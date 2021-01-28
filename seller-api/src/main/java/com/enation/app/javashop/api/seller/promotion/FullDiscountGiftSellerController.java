package com.enation.app.javashop.api.seller.promotion;

import com.enation.app.javashop.model.promotion.fulldiscount.dos.FullDiscountGiftDO;
import com.enation.app.javashop.service.promotion.fulldiscount.FullDiscountGiftManager;
import com.enation.app.javashop.framework.context.user.UserContext;
import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.framework.exception.NoPermissionException;
import com.enation.app.javashop.framework.security.model.Seller;
import com.enation.app.javashop.framework.util.CurrencyUtil;
import com.enation.app.javashop.framework.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 满减满赠促销活动赠品控制器
 *
 * @author Snow
 * @version v7.0.0
 * @since v7.0.0
 * 2018-03-30 17:34:46
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/seller/promotion/full-discount-gifts")
@Api(description = "满减满赠促销活动赠品相关API")
@Validated
public class FullDiscountGiftSellerController {

    @Autowired
    private FullDiscountGiftManager fullDiscountGiftManager;

    @ApiOperation(value = "查询赠品信息分页列表数据", response = FullDiscountGiftDO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_no", value = "页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "keyword", value = "关键字查询", dataType = "String", paramType = "query")
    })
    @GetMapping
    public WebPage list(@ApiIgnore Long pageNo, @ApiIgnore Long pageSize, String keyword) {

        return this.fullDiscountGiftManager.list(pageNo, pageSize, keyword);
    }


    @ApiOperation(value = "添加赠品信息", response = FullDiscountGiftDO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gift_name", value = "赠品名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gift_price", value = "赠品金额", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "gift_img", value = "赠品图片", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "actual_store", value = "库存", required = true, dataType = "int", paramType = "query"),
    })
    @PostMapping
    public FullDiscountGiftDO add(@ApiIgnore @NotEmpty(message = "请填写赠品名称") String giftName,
                                  @ApiIgnore @NotNull(message = "请填写赠品金额") Double giftPrice,
                                  @ApiIgnore @NotEmpty(message = "请上传赠品图片") String giftImg,
                                  @ApiIgnore @NotNull(message = "请填写库存") Integer actualStore) {
        //获取当前登录的商家信息
        Seller seller = UserContext.getSeller();

        //创建赠品实体对象
        FullDiscountGiftDO giftDO = new FullDiscountGiftDO();
        //设置赠品名称
        giftDO.setGiftName(giftName);
        //设置赠品价格
        giftDO.setGiftPrice(giftPrice);
        //设置赠品图片
        giftDO.setGiftImg(giftImg);
        //设置赠品实际库存
        giftDO.setActualStore(actualStore);
        //设置赠品可用库存(添加时可用库存=实际库存)
        giftDO.setEnableStore(actualStore);
        //添加赠品创建时间
        giftDO.setCreateTime(DateUtil.getDateline());
        //设置赠品类型(1.0版本赠品类型默认为0：普通赠品)
        giftDO.setGiftType(0);
        //默认不禁用
        giftDO.setDisabled(0);
        //设置赠品所属店铺id
        giftDO.setSellerId(seller.getSellerId());
        //添加赠品信息
        this.fullDiscountGiftManager.add(giftDO);

        return giftDO;
    }

    @PutMapping(value = "/{gift_id}")
    @ApiOperation(value = "修改赠品信息", response = FullDiscountGiftDO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gift_id", value = "赠品id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "gift_name", value = "赠品名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gift_name", value = "赠品名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gift_price", value = "赠品金额", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "gift_img", value = "赠品图片", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "actual_store", value = "库存", required = true, dataType = "int", paramType = "query"),
    })
    public FullDiscountGiftDO edit(@ApiIgnore @PathVariable("gift_id") Long giftId,
                                   @ApiIgnore @NotEmpty(message = "请填写赠品名称") String giftName,
                                   @ApiIgnore @NotNull(message = "请填写赠品金额") Double giftPrice,
                                   @ApiIgnore @NotEmpty(message = "请上传赠品图片") String giftImg,
                                   @ApiIgnore @NotNull(message = "请填写库存") Integer actualStore) {
        //操作权限校验（如果当前修改的赠品不属于当前操作的商家则不允许操作）
        this.fullDiscountGiftManager.verifyAuth(giftId);
        //获取原赠品信息
        FullDiscountGiftDO fullDiscountGift = fullDiscountGiftManager.getModel(giftId);
        //获取原被占用库存 = 实际库存-可用库存
        double storeSub = CurrencyUtil.sub(fullDiscountGift.getActualStore(), fullDiscountGift.getEnableStore());
        //获取实际可用库存 = 新库存 - 被占用库存
        double enableStore = CurrencyUtil.sub(actualStore, storeSub);
        //获取当前登录商家信息
        Seller seller = UserContext.getSeller();

        //创建赠品实体对象
        FullDiscountGiftDO giftDO = new FullDiscountGiftDO();
        //设置赠品主键ID
        giftDO.setGiftId(giftId);
        //设置赠品名称
        giftDO.setGiftName(giftName);
        //设置赠品价格
        giftDO.setGiftPrice(giftPrice);
        //设置赠品图片
        giftDO.setGiftImg(giftImg);
        //设置赠品实际库存
        giftDO.setActualStore(actualStore);
        //设置赠品可用库存(添加时可用库存=实际库存)
        giftDO.setEnableStore((int)enableStore);
        //添加赠品创建时间
        giftDO.setCreateTime(DateUtil.getDateline());
        //设置赠品类型(1.0版本赠品类型默认为0：普通赠品)
        giftDO.setGiftType(0);
        //默认不禁用
        giftDO.setDisabled(0);
        //设置赠品所属店铺id
        giftDO.setSellerId(seller.getSellerId());
        //修改赠品信息
        this.fullDiscountGiftManager.edit(giftDO, giftId);

        return giftDO;
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除赠品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的赠品主键ID", required = true, dataType = "int", paramType = "path")
    })
    public String delete(@PathVariable Long id) {
        //操作权限校验（如果当前修改的赠品不属于当前操作的商家则不允许操作）
        this.fullDiscountGiftManager.verifyAuth(id);
        //如果赠品参与了满减满赠促销活动并且活动正在进行中，则不允许删除
        this.fullDiscountGiftManager.verifyGift(id);
        //删除赠品信息
        this.fullDiscountGiftManager.delete(id);
        return "";
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询一个赠品新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要查询的赠品主键ID", required = true, dataType = "int", paramType = "path")
    })
    public FullDiscountGiftDO get(@PathVariable Long id) {
        //根据ID获取赠品信息
        FullDiscountGiftDO fullDiscountGift = this.fullDiscountGiftManager.getModel(id);
        //获取当前登录的商家信息
        Seller seller = UserContext.getSeller();
        //验证越权操作（赠品信息为空或者赠品不属于当前登录的商家，提示无权操作）
        if (fullDiscountGift == null || !seller.getSellerId().equals(fullDiscountGift.getSellerId())) {
            throw new NoPermissionException("无权操作");
        }

        return fullDiscountGift;
    }

    @ApiOperation(value = "查询当前登录商家的赠品信息集合", response = FullDiscountGiftDO.class)
    @GetMapping(value = "/all")
    public List<FullDiscountGiftDO> listAll() {
        return this.fullDiscountGiftManager.listAll();
    }

}
