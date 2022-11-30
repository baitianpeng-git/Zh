package com.zh.btp.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.zh.btp.base.Result;
import com.zh.btp.base.StatusCode;
import com.zh.btp.entity.User;
import com.zh.btp.jwt.TokenUtil;
import com.zh.btp.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password, HttpServletResponse servletResponse){

        User user = userService.login(username, password);
        if (!StringUtils.isEmpty(user)){

            //根据用户信息创建token，（登录成功的处理逻辑）
            String token = TokenUtil.createToken(user);
            //设置header
            servletResponse.setHeader("token",token);
            //获取token，获取过期时间进行返回
            DecodedJWT tokenInfo = TokenUtil.getTokenInfo(token);
            Date expiresAt = tokenInfo.getExpiresAt();
            return new Result(true, StatusCode.OK,"登陆成功",token,expiresAt);
        }
        return new Result(false, StatusCode.ERROR,"登陆失败",user);
    }
}
