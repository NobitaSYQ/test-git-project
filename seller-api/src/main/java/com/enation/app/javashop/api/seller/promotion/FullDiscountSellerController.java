package com.enation.app.javashop.api.seller.promotion;

import com.enation.app.javashop.model.promotion.fulldiscount.dos.FullDiscountDO;
import com.enation.app.javashop.model.promotion.fulldiscount.vo.FullDiscountVO;
import com.enation.app.javashop.service.promotion.fulldiscount.FullDiscountManager;
import com.enation.app.javashop.framework.context.user.UserContext;
import com.enation.app.javashop.model.util.PromotionValid;
import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.framework.exception.NoPermissionException;
import com.enation.app.javashop.framework.exception.ServiceException;
import com.enation.app.javashop.framework.exception.SystemErrorCodeV1;
import com.enation.app.javashop.framework.security.model.Seller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * 满减满赠促销活动控制器
 * @author Snow
 * @version v7.0.0
 * @since v7.0.0
 * 2018-03-30 17:34:32
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/seller/promotion/full-discounts")
@Api(description = "满减满赠促销活动相关API")
@Validated
public class FullDiscountSellerController	{

	@Autowired
	private FullDiscountManager fullDiscountManager;


	@ApiOperation(value	= "查询满减满赠促销活动信息分页数据列表", response = FullDiscountDO.class)
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "page_no",	value =	"页码",	dataType = "int",	paramType =	"query"),
		 @ApiImplicitParam(name	= "page_size",	value =	"每页显示数量",	 dataType = "int",	paramType =	"query"),
		 @ApiImplicitParam(name	= "keywords",	value =	"搜索关键字", dataType = "String",	paramType =	"query")
	})
	@GetMapping
	public WebPage<FullDiscountVO> list(@ApiIgnore Long pageNo, @ApiIgnore Long pageSize, @ApiIgnore String keywords)	{

		return	this.fullDiscountManager.list(pageNo,pageSize,keywords);
	}


	@ApiOperation(value	= "添加满减满赠促销活动信息", response = FullDiscountVO.class)
	@PostMapping
	public FullDiscountVO add(@Valid @RequestBody FullDiscountVO fullDiscountVO)	{
		//验证满减满赠促销活动的参数信息
		this.verifyFullDiscountParam(fullDiscountVO);
		//获取当前登录的商家信息
		Seller seller = UserContext.getSeller();
		//获取商家ID
		Long sellerId = seller.getSellerId();
		//设置活动所属的商家ID
		fullDiscountVO.setSellerId(sellerId);
		//添加满减满赠促销活动
		this.fullDiscountManager.add(fullDiscountVO);
		return fullDiscountVO;
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value	= "修改满减满赠促销活动信息", response = FullDiscountDO.class)
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "id",	value =	"满减满赠促销活动主键ID",	required = true, dataType = "int",	paramType =	"path")
	})
	public FullDiscountVO edit(@Valid @RequestBody FullDiscountVO fullDiscountVO, @PathVariable Long id) {
		//设置活动ID
		fullDiscountVO.setFdId(id);
		//验证满减满赠促销活动的参数信息
		this.verifyFullDiscountParam(fullDiscountVO);
		//校验当前修改的满减满赠活动是否属于当前登录的商家
		this.fullDiscountManager.verifyAuth(id);
		//修改满减满赠促销活动
		this.fullDiscountManager.edit(fullDiscountVO,id);
		return fullDiscountVO;
	}


	@DeleteMapping(value = "/{id}")
	@ApiOperation(value	= "删除满优惠活动")
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "id",	value =	"要删除的满减满赠促销活动主键ID",	required = true, dataType = "int",	paramType =	"path")
	})
	public String delete(@PathVariable Long id) {
		//校验当前删除的满减满赠活动是否属于当前登录的商家
		this.fullDiscountManager.verifyAuth(id);
		//删除满减满赠活动信息
		this.fullDiscountManager.delete(id);
		return "";
	}


	@GetMapping(value =	"/{id}")
	@ApiOperation(value	= "查询一个满减满赠促销活动信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id",	value = "要查询的满减满赠促销活动主键ID",	required = true, dataType = "int",	paramType = "path")
	})
	public FullDiscountVO get(@PathVariable Long id) {
		//根据主键ID获取满减满赠促销活动信息
		FullDiscountVO fullDiscount = this.fullDiscountManager.getModel(id);

		//获取当前登录的商家信息
		Seller seller = UserContext.getSeller();
		//验证越权操作（如果获取的满减满赠促销活动信息不属于当前登录的商家则提示无权操作）
		if (fullDiscount == null || !seller.getSellerId().equals(fullDiscount.getSellerId())){
			throw new NoPermissionException("无权操作");
		}

		return fullDiscount;
	}


	/**
	 * 验证满减满赠促销活动的参数信息
	 * @param fullDiscountVO 满减满赠促销活动信息
	 */
	private void verifyFullDiscountParam(FullDiscountVO fullDiscountVO){
		//验证满减满赠促销活动的活动时间以及商品参与活动的方式
		PromotionValid.paramValid(fullDiscountVO.getStartTime(),fullDiscountVO.getEndTime(),
				fullDiscountVO.getRangeType(),fullDiscountVO.getGoodsList());

		//促销活动的优惠方式不能全部为空，至少要选择一项
		if (fullDiscountVO.getIsFullMinus() == null && fullDiscountVO.getIsFreeShip() == null && fullDiscountVO.getIsSendGift() == null
				&& fullDiscountVO.getIsSendBonus() == null && fullDiscountVO.getIsDiscount() == null) {

			throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"请选择优惠方式");
		}

		// 如果促销活动优惠详细是否包含满减不为空
		if (fullDiscountVO.getIsFullMinus() != null && fullDiscountVO.getIsFullMinus() == 1) {

			if (fullDiscountVO.getMinusValue() == null || fullDiscountVO.getMinusValue() == 0) {
				throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"请填写减多少元");
			}

			// 减少的现金必须小于优惠门槛
			if (fullDiscountVO.getMinusValue() > fullDiscountVO.getFullMoney()) {
				throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"减少的金额不能大于门槛金额");
			}

		}

		// 如果促销活动优惠详细是否包含满送赠品不为空
		if (fullDiscountVO.getIsSendGift() != null && fullDiscountVO.getIsSendGift() == 1) {
			// 赠品id不能为0
			if (fullDiscountVO.getGiftId() == null || fullDiscountVO.getGiftId() == 0) {
				throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"请选择赠品");
			}
		}

		// 如果促销活动优惠详细是否包含满送优惠券不为空
		if (fullDiscountVO.getIsSendBonus() != null && fullDiscountVO.getIsSendBonus() == 1) {
			// 优惠券id不能为0
			if (fullDiscountVO.getBonusId() == null || fullDiscountVO.getBonusId() == 0) {
				throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"请选择优惠券");
			}
		}

		// 如果促销活动优惠详细是否包含打折不为空
		if (fullDiscountVO.getIsDiscount() != null && fullDiscountVO.getIsDiscount() == 1) {
			// 打折的数值不能为空也不能为0
			if (fullDiscountVO.getDiscountValue() == null || fullDiscountVO.getDiscountValue() == 0) {

				throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"请填写打折数值");
			}
			// 打折的数值应介于0-10之间
			if (fullDiscountVO.getDiscountValue() >= 10 || fullDiscountVO.getDiscountValue() <= 0) {
				throw new ServiceException(SystemErrorCodeV1.INVALID_REQUEST_PARAMETER,"打折的数值应介于0到10之间");
			}
		}

	}
}
