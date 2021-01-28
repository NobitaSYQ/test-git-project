package com.enation.app.javashop.api.seller.promotion;

import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.model.promotion.minus.dos.MinusDO;
import com.enation.app.javashop.model.promotion.minus.vo.MinusVO;
import com.enation.app.javashop.service.promotion.minus.MinusManager;
import com.enation.app.javashop.framework.context.user.UserContext;
import com.enation.app.javashop.model.util.PromotionValid;
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
 * 单品立减促销活动控制器
 * @author Snow
 * @version v7.0.0
 * @since v7.0.0
 * 2018-03-23 19:52:27
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/seller/promotion/minus")
@Api(description = "单品立减促销活动相关API")
@Validated
public class MinusSellerController {

	@Autowired
	private MinusManager minusManager;

	@ApiOperation(value	= "查询单品立减促销活动分页数据列表", response = MinusDO.class)
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "page_no",	value =	"页码", dataType = "int",	paramType =	"query"),
		 @ApiImplicitParam(name	= "page_size",	value =	"每页显示数量", dataType = "int",	paramType =	"query"),
		 @ApiImplicitParam(name	= "keywords",	value =	"查询关键字", dataType = "String",	paramType =	"query")
	})
	@GetMapping
	public WebPage<MinusVO> list(@ApiIgnore Long pageNo, @ApiIgnore Long pageSize, String keywords)	{
		return	this.minusManager.list(pageNo,pageSize,keywords);
	}


	@ApiOperation(value	= "添加单品立减促销活动信息", response = MinusVO.class)
	@ApiImplicitParam(name = "minus", value = "单品立减促销活动信息", required = true, dataType = "MinusVO", paramType = "body")
	@PostMapping
	public MinusVO add(@ApiIgnore @Valid @RequestBody MinusVO minus) {
		//参数验证 活动时间和参与活动商品的验证
		PromotionValid.paramValid(minus.getStartTime(),minus.getEndTime(),minus.getRangeType(),minus.getGoodsList());
		// 获取当前登录的商家信息
		Seller seller = UserContext.getSeller();
		//获取商家ID
		Long sellerId = seller.getSellerId();
		//设置商家ID
		minus.setSellerId(sellerId);
		//添加单品立减促销活动信息
		this.minusManager.add(minus);
		return minus;

	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value	= "修改单品立减促销活动信息", response = MinusVO.class)
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "id",	value =	"单品立减促销活动主键ID",	required = true, dataType = "int",	paramType =	"path")
	})
	public MinusVO edit(@Valid @RequestBody MinusVO minus, @PathVariable Long id) {
		//操作权限校验（如果当前修改的单品立减活动不属于当前操作的商家则不允许操作）
		this.minusManager.verifyAuth(id);
		//参数验证 活动时间和参与活动商品的验证
		PromotionValid.paramValid(minus.getStartTime(),minus.getEndTime(),minus.getRangeType(),minus.getGoodsList());
		//设置单品立减活动ID
		minus.setMinusId(id);
		//修改单品立减促销活动信息
		this.minusManager.edit(minus,id);
		return minus;
	}


	@DeleteMapping(value = "/{id}")
	@ApiOperation(value	= "删除单品立减促销活动信息")
	@ApiImplicitParams({
		 @ApiImplicitParam(name	= "id",	value =	"要删除的单品立减促销活动信息主键ID",	required = true, dataType = "int",	paramType =	"path")
	})
	public String delete(@PathVariable Long id) {
		//操作权限校验（如果当前修改的单品立减活动不属于当前操作的商家则不允许操作）
		this.minusManager.verifyAuth(id);
		//删除单品立减促销活动信息
		this.minusManager.delete(id);
		return "";
	}


	@GetMapping(value =	"/{id}")
	@ApiOperation(value	= "查询一个单品立减促销活动信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id",	value = "要查询的单品立减促销活动信息主键ID",	required = true, dataType = "int",	paramType = "path")
	})
	public MinusVO get(@PathVariable Long id)	{
		//根据主键ID获取单品立减促销活动信息
		MinusVO minusVO = this.minusManager.getFromDB(id);
		//获取当前登录的商家信息
		Seller seller = UserContext.getSeller();

		//验证越权操作
		if (minusVO == null || !seller.getSellerId().equals(minusVO.getSellerId()) ){
			throw new NoPermissionException("无权操作");
		}

		return minusVO;
	}


}
