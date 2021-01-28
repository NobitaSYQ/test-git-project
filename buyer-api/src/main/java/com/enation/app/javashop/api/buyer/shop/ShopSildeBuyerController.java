package com.enation.app.javashop.api.buyer.shop;

import com.enation.app.javashop.model.shop.dos.ShopSildeDO;
import com.enation.app.javashop.service.shop.ShopSildeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺幻灯片控制器
 * @author zjp
 * @version v7.0.0
 * @since v7.0.0
 * 2018-03-28 18:50:58
 */
@RestController
@RequestMapping("/shops/sildes")
@Api(description = "店铺幻灯片相关API")
@Validated
public class ShopSildeBuyerController {

	@Autowired
	private	ShopSildeManager shopSildeManager;


	@ApiOperation(value	= "查询店铺幻灯片列表", response = ShopSildeDO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shop_id" , value = "店铺id" ,required = true , dataType = "long" , paramType = "path" ),
			@ApiImplicitParam(name	= "client_type",	value =	"客户端MOBILE/PC",	required = true, dataType = "string",	paramType =	"query")
	})
	@GetMapping("/{shop_id}")
	public List<ShopSildeDO> list(@PathVariable("shop_id") Long shopId,String clientType)	{
		return	this.shopSildeManager.list(shopId,clientType);
	}

}
