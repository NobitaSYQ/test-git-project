package com.enation.app.javashop.api.buyer.debugger;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.enation.app.javashop.client.goods.GoodsClient;
import com.enation.app.javashop.framework.cache.Cache;
import com.enation.app.javashop.framework.context.user.UserContext;
import com.enation.app.javashop.framework.exception.ServiceException;
import com.enation.app.javashop.framework.exception.SystemErrorCodeV1;
import com.enation.app.javashop.framework.trigger.Interface.TimeTrigger;
import com.enation.app.javashop.framework.util.DateUtil;
import com.enation.app.javashop.framework.util.JsonUtil;
import com.enation.app.javashop.framework.util.StringUtil;
import com.enation.app.javashop.mapper.promotion.PromotionGoodsMapper;
import com.enation.app.javashop.mapper.promotion.seckill.SeckillApplyMapper;
import com.enation.app.javashop.mapper.promotion.seckill.SeckillMapper;
import com.enation.app.javashop.model.base.message.PromotionScriptMsg;
import com.enation.app.javashop.model.base.rabbitmq.TimeExecute;
import com.enation.app.javashop.model.errorcode.PromotionErrorCode;
import com.enation.app.javashop.model.errorcode.SystemErrorCode;
import com.enation.app.javashop.model.goods.vo.CacheGoods;
import com.enation.app.javashop.model.goods.vo.GoodsSkuVO;
import com.enation.app.javashop.model.promotion.seckill.dos.SeckillApplyDO;
import com.enation.app.javashop.model.promotion.seckill.dos.SeckillDO;
import com.enation.app.javashop.model.promotion.seckill.enums.SeckillGoodsApplyStatusEnum;
import com.enation.app.javashop.model.promotion.seckill.enums.SeckillStatusEnum;
import com.enation.app.javashop.model.promotion.seckill.vo.SeckillApplyVO;
import com.enation.app.javashop.model.promotion.seckill.vo.SeckillGoodsVO;
import com.enation.app.javashop.model.promotion.seckill.vo.SeckillVO;
import com.enation.app.javashop.model.promotion.tool.dos.PromotionGoodsDO;
import com.enation.app.javashop.model.promotion.tool.dto.PromotionDetailDTO;
import com.enation.app.javashop.model.promotion.tool.dto.PromotionPriceDTO;
import com.enation.app.javashop.model.promotion.tool.enums.PromotionTypeEnum;
import com.enation.app.javashop.model.promotion.tool.enums.ScriptOperationTypeEnum;
import com.enation.app.javashop.model.system.enums.DeleteStatusEnum;
import com.enation.app.javashop.service.goods.GoodsSkuManager;
import com.enation.app.javashop.service.promotion.seckill.SeckillManager;
import com.enation.app.javashop.service.promotion.seckill.SeckillRangeManager;
import com.enation.app.javashop.service.promotion.seckill.SeckillScriptManager;
import com.enation.app.javashop.service.promotion.tool.PromotionGoodsManager;
import com.enation.app.javashop.service.promotion.tool.support.PromotionCacheKeys;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/debugger/seckill")
@ConditionalOnProperty(value = "javashop.debugger", havingValue = "true")
public class SeckillCreateController {

    @Autowired
    private SeckillManager seckillManager;

    @Autowired
    private GoodsSkuManager goodsSkuManager;

    @Autowired
    private PromotionGoodsMapper promotionGoodsMapper;

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillApplyMapper seckillApplyMapper;

    @Autowired
    private SeckillRangeManager seckillRangeManager;

    @Autowired
    private TimeTrigger timeTrigger;

    @Autowired
    private PromotionGoodsManager promotionGoodsManager;

    @Autowired
    private SeckillScriptManager seckillScriptManager;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private Cache cache;

    @ApiOperation(value = "添加限时抢购入库", response = SeckillVO.class)
    @GetMapping
    @Transactional(value = "tradeTransactionManager",propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, ServiceException.class})
    public SeckillVO add(@NotNull String startTime,@NotNull String endTime,@NotNull String times,@NotNull String skuIds) {
        SeckillVO seckill = new SeckillVO();
        seckill.setApplyEndTime(DateUtil.getDateline(endTime,"yyyy-MM-dd HH:mm:ss"));
        seckill.setStartDay(DateUtil.getDateline(startTime,"yyyy-MM-dd"));
        seckill.setSeckillName("测试" + startTime);
        seckill.setRangeList(getRangeList(times));
        seckill.setSeckillStatus(SeckillStatusEnum.RELEASE.name());
        //验证活动名称是否为空
        this.checkName(seckill.getSeckillName(), null);


        SeckillDO seckillDO = new SeckillDO();
        seckillDO.setDeleteStatus(DeleteStatusEnum.NORMAL.value());
        BeanUtils.copyProperties(seckill, seckillDO);
        seckillMapper.insert(seckillDO);

        Long id = seckillDO.getSeckillId();

        this.seckillRangeManager.addList(seckill.getRangeList(), id);

        //开启延时任务执行器
        openTimeExecuter(seckill, DateUtil.getDateline(endTime,"yyyy-MM-dd HH:mm:ss"), id);
        seckill.setSeckillId(id);
        addSeckillGoods(seckill,skuIds);
        return seckill;
    }


    @Transactional(value = "tradeTransactionManager",propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, ServiceException.class})
    void addSeckillGoods(SeckillVO seckill, String skuIds){
        List<String> skuIdList = this.getSkuIdList(skuIds,";");
        for (int i = 0; i < skuIdList.size(); i++) {
            List<String> skuidList = this.getSkuIdList(skuIdList.get(i),",");
            for (String skuidStr: skuidList) {
                Long skuid = StringUtil.toLong(skuidStr,0);
                GoodsSkuVO goodsSkuVO = goodsSkuManager.getSkuFromCache(skuid);
                if (goodsSkuVO == null ){
                    continue;
                }
                SeckillApplyDO seckillApplyDO = new SeckillApplyDO();
                seckillApplyDO.setSeckillId(seckill.getSeckillId());
                seckillApplyDO.setGoodsId(goodsSkuVO.getGoodsId());
                seckillApplyDO.setSkuId(skuid);
                seckillApplyDO.setGoodsName(goodsSkuVO.getGoodsName());
                seckillApplyDO.setOriginalPrice(goodsSkuVO.getPrice());
                seckillApplyDO.setPrice(0.01);
                seckillApplyDO.setStatus(SeckillGoodsApplyStatusEnum.PASS.name());
                seckillApplyDO.setSellerId(goodsSkuVO.getSellerId());
                seckillApplyDO.setShopName(goodsSkuVO.getSellerName());
                seckillApplyDO.setSalesNum(0);
                seckillApplyDO.setSpecs(goodsSkuVO.getSpecs());
                seckillApplyDO.setSoldQuantity(1);
                seckillApplyDO.setStartDay(seckill.getStartDay());
                seckillApplyDO.setTimeLine(seckill.getRangeList().get(i));
                Long skuId = seckillApplyDO.getSkuId();
                //判断参加活动的数量和库存数量
                if (seckillApplyDO.getSoldQuantity() > goodsSkuVO.getEnableQuantity()) {
                    throw new ServiceException(PromotionErrorCode.E402.code(), seckillApplyDO.getGoodsName() + ",此商品库存不足");
                }

                /**
                 * *************两种情况：******************
                 * 团购时间段：      |________________|
                 * 秒杀时间段：  |_____|           |_______|
                 *
                 * ************第三种情况：******************
                 * 团购时间段：        |______|
                 * 秒杀时间段：   |________________|
                 *
                 * ************第四种情况：******************
                 * 团购时间段：   |________________|
                 * 秒杀时间段：        |______|
                 *
                 */
                //这个商品的开始时间计算要用他参与的时间段来计算，结束时间是当天晚上23：59：59
                String date = DateUtil.toString(seckill.getStartDay(), "yyyy-MM-dd");
                long startTime = DateUtil.getDateline(date + " " + seckillApplyDO.getTimeLine() + ":00:00", "yyyy-MM-dd HH:mm:ss");
                long endTime = DateUtil.getDateline(date + " 23:59:59", "yyyy-MM-dd HH:mm:ss");

                int count = promotionGoodsMapper.selectCountByTime(skuId, startTime, endTime);

                if (count > 0) {
                    throw new ServiceException(PromotionErrorCode.E400.code(), "商品[" + goodsSkuVO.getGoodsName() + "]已经在重叠的时间段参加了团购活动，不能参加限时抢购活动");
                }
                seckillApplyMapper.insert(seckillApplyDO);
                long applyId = seckillApplyDO.getApplyId();
                seckillApplyDO.setApplyId(applyId);
                this.seckillManager.sellerApply(goodsSkuVO.getSellerId(), seckill.getSeckillId());
                //参与限时抢购促销活动并且已被平台审核通过的商品集合
                List<SeckillApplyDO> goodsList = new ArrayList<>();
                //审核通过的限时抢购商品集合
                List<PromotionGoodsDO> promotionGoodsDOS = new ArrayList<>();
                Long actId = 0L;

                SeckillApplyDO apply = seckillApplyDO;

                //查询商品
                CacheGoods goods = goodsClient.getFromCache(apply.getGoodsId());
                    //将审核通过的商品放入集合中
                    goodsList.add(apply);

                    //促销商品表
                    PromotionGoodsDO promotion = new PromotionGoodsDO();
                    promotion.setTitle("限时抢购");
                    promotion.setGoodsId(apply.getGoodsId());
                    promotion.setSkuId(apply.getSkuId());
                    promotion.setPromotionType(PromotionTypeEnum.SECKILL.name());
                    promotion.setActivityId(apply.getSeckillId());
                    promotion.setNum(apply.getSoldQuantity());
                    promotion.setPrice(apply.getPrice());
                    promotion.setSellerId(goods.getSellerId());
                    promotion.setStartTime(startTime);
                    promotion.setEndTime(endTime);
                    promotionGoodsDOS.add(promotion);


                    //从缓存读取限时抢购的活动的商品
                    String redisKey = getRedisKey(apply.getStartDay());
                    Map<Integer, List<SeckillGoodsVO>> map = this.cache.getHash(redisKey);
                    //如果redis中有当前审核商品参与的限时抢购活动商品信息，就删除掉
                    if (map != null && !map.isEmpty()) {
                        this.cache.remove(redisKey);
                    }

                    //设置延迟加载任务，到活动开始时间后将搜索引擎中的优惠价格设置为0
                    PromotionPriceDTO promotionPriceDTO = new PromotionPriceDTO();
                    promotionPriceDTO.setGoodsId(apply.getGoodsId());
                    promotionPriceDTO.setPrice(apply.getPrice());
                    timeTrigger.add(TimeExecute.PROMOTION_EXECUTER, promotionPriceDTO, startTime, null);
                    //此活动结束后将索引的优惠价格重置为0
                    promotionPriceDTO.setPrice(0.0);
                    timeTrigger.add(TimeExecute.PROMOTION_EXECUTER, promotionPriceDTO, endTime, null);

                //活动信息DTO
                PromotionDetailDTO detailDTO = new PromotionDetailDTO();
                detailDTO.setActivityId(seckill.getSeckillId());
                detailDTO.setStartTime(startTime);
                detailDTO.setEndTime(endTime);
                detailDTO.setPromotionType(PromotionTypeEnum.SECKILL.name());
                detailDTO.setTitle("限时抢购");
                this.promotionGoodsManager.addAndCheck(promotionGoodsDOS, detailDTO);
                //创建审核通过的商品限时抢购促销活动脚本信息
                this.seckillScriptManager.createCacheScript(goodsList.get(0).getSeckillId(), goodsList);
            }

        }
    }





    /**
     * 转换商品ID数据
     * @param skuIds
     * @return
     */
    private List<String> getSkuIdList(String skuIds,String split){
        if (StringUtil.isEmpty(skuIds)) {
            throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"参数错误");
        }
        String[] timesS = skuIds.split(split);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < timesS.length; i++) {
            list.add(timesS[i]);
        }
        return list;
    }

    /**
     * 转换时刻数据结构
     * @param times
     * @return
     */
    private List<Integer> getRangeList(String times){
        if (StringUtil.isEmpty(times)) {
            throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"参数错误");
        }
        String[] timesS = times.split(",");
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < timesS.length; i++) {
            list.add(StringUtil.toInt(timesS[i]));
        }
        return list;
    }

    /**
     * 验证活动名称是否重复
     *
     * @param name
     * @param seckillId
     */
    private void checkName(String name, Long seckillId) {

        List<SeckillDO> list = new QueryChainWrapper<>(seckillMapper)
                //拼接活动名称查询条件
                .eq("seckill_name", name)
                //id不为空
                .ne(seckillId != null, "seckill_id", seckillId)
                //列表查询
                .list();

//        if (list.size() > 0) {
//            throw new ServiceException(PromotionErrorCode.E400.code(), "活动名称重复");
//        }

    }

    /**
     * 开启延时任务执行器
     *
     * @param seckill 限时抢购信息
     * @param endTime 活动结束时间
     * @param id      限时抢购活动ID
     */
    private void openTimeExecuter(SeckillVO seckill, long endTime, Long id) {
        //如果活动状态是已发布
        if (SeckillStatusEnum.RELEASE.value().equals(seckill.getSeckillStatus())) {
            //启用延时任务,限时抢购促销活动结束时删除脚本信息
            PromotionScriptMsg promotionScriptMsg = new PromotionScriptMsg();
            promotionScriptMsg.setPromotionId(id);
            promotionScriptMsg.setPromotionName(seckill.getSeckillName());
            promotionScriptMsg.setPromotionType(PromotionTypeEnum.SECKILL);
            promotionScriptMsg.setOperationType(ScriptOperationTypeEnum.DELETE);
            promotionScriptMsg.setEndTime(endTime);
            String uniqueKey = "{TIME_TRIGGER_" + PromotionTypeEnum.SECKILL.name() + "}_" + id;
            timeTrigger.add(TimeExecute.SECKILL_SCRIPT_EXECUTER, promotionScriptMsg, endTime, uniqueKey);
        }
    }

    /**
     * 获取限时抢购key
     *
     * @param dateline
     * @return
     */
    private String getRedisKey(long dateline) {
        return PromotionCacheKeys.getSeckillKey(DateUtil.toString(dateline, "yyyyMMdd"));
    }

}
