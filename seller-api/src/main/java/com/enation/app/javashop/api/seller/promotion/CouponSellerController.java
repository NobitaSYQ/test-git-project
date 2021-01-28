package com.enation.app.javashop.api.seller.promotion;

import com.enation.app.javashop.model.promotion.coupon.dos.CouponDO;
import com.enation.app.javashop.model.promotion.coupon.dto.CouponParams;
import com.enation.app.javashop.service.promotion.coupon.CouponManager;
import com.enation.app.javashop.framework.context.user.UserContext;
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
import java.util.List;

/**
 * 店铺优惠券控制器
 *
 * @author Snow
 * @version v2.0
 * @since v7.0.0
 * 2018-04-17 23:19:39
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/seller/promotion/coupons")
@Api(description = "店铺优惠券相关API")
@Validated
public class CouponSellerController {

    @Autowired
    private CouponManager couponManager;

    @ApiOperation(value = "查询店铺优惠券分页数据列表", response = CouponDO.class)
    @GetMapping
    public WebPage list(@ApiIgnore Long pageNo, @ApiIgnore Long pageSize, CouponParams params) {
        params.setPageNo(pageNo);
        params.setPageSize(pageSize);
        //获取当前登录的商家信息
        Seller seller = UserContext.getSeller();
        //设置商家ID作为查询条件
        params.setSellerId(seller.getSellerId());
        return this.couponManager.list(params);
    }

    @ApiOperation(value = "新增店铺优惠券信息", response = CouponDO.class)
    @PostMapping
    public CouponDO add(@Valid CouponDO couponDO) {
        //获取当前登录的商家信息
        Seller seller = UserContext.getSeller();
        //设置优惠券所属商家ID
        couponDO.setSellerId(seller.getSellerId());
        //设置优惠券所属商家名称
        couponDO.setSellerName(seller.getSellerName());
        //新增店铺优惠券信息
        this.couponManager.add(couponDO);
        return couponDO;
    }

    @PutMapping(value = "/{coupon_id}")
    @ApiOperation(value = "修改店铺优惠券信息", response = CouponDO.class)
    @ApiImplicitParam(name = "coupon_id", value = "优惠券主键ID", required = true, dataType = "int", paramType = "path")
    public CouponDO add(@ApiIgnore @PathVariable("coupon_id") Long couponId, @Valid CouponDO couponDO) {
        //设置优惠券主键ID
        couponDO.setCouponId(couponId);
        //获取当前登录的商家信息
        Seller seller = UserContext.getSeller();
        //设置优惠券所属商家ID
        couponDO.setSellerId(seller.getSellerId());
        //设置优惠券所属商家名称
        couponDO.setSellerName(seller.getSellerName());
        //修改店铺优惠券信息
        this.couponManager.edit(couponDO, couponId);
        return couponDO;
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除店铺优惠券信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的优惠券主键ID", required = true, dataType = "int", paramType = "path")
    })
    public String delete(@PathVariable Long id) {
        //操作权限校验（如果当前删除的优惠券不属于当前操作的商家则不允许操作）
        this.couponManager.verifyAuth(id);
        //删除优惠券信息
        this.couponManager.delete(id);
        return "";
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询一个店铺优惠券信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要查询的优惠券主键ID", required = true, dataType = "int", paramType = "path")
    })
    public CouponDO get(@PathVariable Long id) {
        //根据ID获取店铺优惠券信息
        CouponDO coupon = this.couponManager.getModel(id);
        //操作权限校验（如果当前删除的优惠券为空或者不属于当前操作的商家则不允许操作）
        if (coupon == null || !coupon.getSellerId().equals(UserContext.getSeller().getSellerId())) {
            throw new NoPermissionException("无权操作或者数据不存在");
        }
        return coupon;
    }

    @GetMapping(value = "/{status}/list")
    @ApiOperation(value = "根据优惠券状态获取优惠券数据集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "优惠券状态 0：全部，1：有效，2：失效", required = true, dataType = "int", paramType = "path", allowableValues = "0,1,2", example = "0：全部，1：有效，2：失效")
    })
    public List<CouponDO> getByStatus(@PathVariable Integer status) {
        return this.couponManager.getByStatus(status);
    }

}
