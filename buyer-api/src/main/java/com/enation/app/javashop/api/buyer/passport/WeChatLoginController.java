package com.enation.app.javashop.api.buyer.passport;


import com.enation.app.javashop.model.member.dto.WeChatMiniLoginDTO;
import com.enation.app.javashop.model.member.dto.WeChatUserDTO;
import com.enation.app.javashop.service.passport.LoginWeChatManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信统一登陆
 *
 * @author cs
 * @version v1.0
 * @since v7.2.2
 * 2019-09-22
 */
@Api(description = "微信统一登陆")
@RestController
@RequestMapping("/wechat")
@Validated
public class WeChatLoginController {

    @Autowired
    private LoginWeChatManager loginWeChatManager;

    @ApiOperation(value = "获取授权页地址")
    @ApiImplicitParam(name	= "redirectUri",	value =	"授权成功跳转地址(需要urlEncode整体加密)",	required = true, dataType = "string",	paramType =	"query")
    @GetMapping("/wap/getLoginUrl")
    public String getLoginUrl(@RequestParam("redirectUri") String  redirectUri){
        return  loginWeChatManager.getLoginUrl(redirectUri);
    }

    @ApiOperation(value = "网页登陆")
    @GetMapping("/wap/login")
    public Map h5Login(String code,String uuid){
        return loginWeChatManager.wxWapLogin(code,uuid);
    }

    @ApiOperation(value = "app登陆")
    @PostMapping("/app/login/{uuid}")
    public Map appLogin(@PathVariable String uuid, WeChatUserDTO weChatUserDTO){
        return loginWeChatManager.wxAppLogin(uuid,weChatUserDTO);
    }

    @ApiOperation(value = "小程序登陆")
    @PostMapping("/mini/login")
    public Map miniLogin(WeChatMiniLoginDTO weChatMiniLoginDTO){
        return loginWeChatManager.miniLogin(weChatMiniLoginDTO);
    }

    @ApiOperation(value = "小程序绑定手机号")
    @PostMapping("/mini/bind/phone")
    public Map miniBindPhone( String encrypted,String iv){
        return loginWeChatManager.miniBindPhone(encrypted,iv);
    }

    @GetMapping("/h5/openid")
    public String getH5Openid(String code) {
        JSONObject accessTokenJson =  loginWeChatManager.getAccessToken(code);
        String openid = accessTokenJson.getString("openid");
        return openid;
    }

    @GetMapping("/mini/openid")
    public String getMiniOpenid(String code) {
        String openid = loginWeChatManager.getMiniOpenid(code);
        return openid;
    }

}
