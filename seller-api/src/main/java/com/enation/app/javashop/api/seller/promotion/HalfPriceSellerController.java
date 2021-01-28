package com.enation.app.javashop.api.seller.promotion;

import com.enation.app.javashop.model.promotion.halfprice.dos.HalfPriceDO;
import com.enation.app.javashop.model.promotion.halfprice.vo.HalfPriceVO;
import com.enation.app.javashop.service.promotion.halfprice.HalfPriceManager;
import com.enation.app.javashop.framework.context.user.UserContext;
import com.enation.app.javashop.model.util.PromotionValid;
import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.framework.exception.NoPermissionException;
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
 * 第二件半价促销活动控制器
 * @author Snow
 * @version v7.0.0
 * @since v7.0.0
 * 2018-03-23 19:53:42
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/seller/promotion/half-prices")
@Api(description = "第二件半价促销活动相关API")
@Validated
public class HalfPriceSellerController	{

	@Autowired
	private HalfPriceManager halfPriceManager;

	@ApiOperation(value	= "查询第二件半价促销活动分页数据列表", response = HalfPriceDO.class)
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "page_no",	value =	"页码",dataType = "int",	paramType =	"query"),
		 @ApiImplicitParam(name	= "page_size",	value =	"每页显示数量", dataType = "int",	paramType =	"query"),
		 @ApiImplicitParam(name	= "keywords",	value =	"查询关键字", dataType = "String",	paramType =	"query")
	})
	@GetMapping
	public WebPage<HalfPriceVO> list(@ApiIgnore Long pageNo, @ApiIgnore Long pageSize, @ApiIgnore String keywords)	{
		return this.halfPriceManager.list(pageNo,pageSize,keywords);
	}

	@ApiOperation(value	= "添加第二件半价促销活动信息", response = HalfPriceVO.class)
	@PostMapping
	public HalfPriceVO add(@Valid @RequestBody HalfPriceVO halfPrice)	{
		//参数验证 活动时间和参与活动商品的验证
		PromotionValid.paramValid(halfPrice.getStartTime(),halfPrice.getEndTime(),
				halfPrice.getRangeType(),halfPrice.getGoodsList());
		//获取当前登录的商家信息
		Seller seller = UserContext.getSeller();
		//获取商家ID
		Long sellerId = seller.getSellerId();
		//为活动设置商家ID
		halfPrice.setSellerId(sellerId);
		//添加第二件半价促销活动信息
		this.halfPriceManager.add(halfPrice);
		return halfPrice;
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value	= "修改第二件半价促销活动信息", response = HalfPriceDO.class)
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "id",	value =	"第二件半价促销活动主键ID",	required = true, dataType = "int",	paramType =	"path")
	})
	public HalfPriceVO edit(@Valid @RequestBody HalfPriceVO halfPrice, @PathVariable Long id) {
		//参数验证 活动时间和参与活动商品的验证
		PromotionValid.paramValid(halfPrice.getStartTime(),halfPrice.getEndTime(),
				halfPrice.getRangeType(),halfPrice.getGoodsList());
		//设置主键ID
		halfPrice.setHpId(id);
		//操作权限校验（如果当前修改的第二件半价活动不属于当前操作的商家则不允许操作）
		this.halfPriceManager.verifyAuth(id);
		//修改第二件半价促销活动信息
		this.halfPriceManager.edit(halfPrice,id);
		return halfPrice;
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value	= "删除第二件半价促销活动信息")
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "id",	value =	"要删除的第二件半价促销活动主键ID",	required = true, dataType = "int",	paramType =	"path")
	})
	public String delete(@PathVariable Long id) {
		//操作权限校验（如果当前删除的第二件半价活动不属于当前操作的商家则不允许操作）
		this.halfPriceManager.verifyAuth(id);
		//删除第二件半价促销活动信息
		this.halfPriceManager.delete(id);
		return "";
	}

	@GetMapping(value =	"/{id}")
	@ApiOperation(value	= "查询一个第二件半价促销活动信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id",	value = "要查询的第二件半价活动主键ID",	required = true, dataType = "int",	paramType = "path")
	})
	public HalfPriceVO get(@PathVariable Long id) {
		//根据ID获取第二件半价促销活动信息
		HalfPriceVO halfPrice = this.halfPriceManager.getFromDB(id);
		//获取当前登录的商家信息
		Seller seller = UserContext.getSeller();
		//验证越权操作
		if (halfPrice == null || !seller.getSellerId().equals(halfPrice.getSellerId())){
			throw new NoPermissionException("无权操作");
		}

		return halfPrice;
	}

}
