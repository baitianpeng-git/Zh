package com.zh.btp.jwt;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.btp.base.Result;
import com.zh.btp.base.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("进入拦截器");
        //实际这个名字可以指定为别的，token太没有辨识度---
        //这个header是在创建完token返回给前端时指定的头部的key，vakue就是token内容
        String token=httpServletRequest.getHeader("token");
        Map map = new HashMap<>();
        Result result = new Result();
        try {
        	//这里尽行token验证，捕获异常，正常的话也不需要处理，直接抛出异常，由统一异常处理类进行处理，然后返回给前端统一数据类型。
            TokenUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
//            e.printStackTrace();
            log.info("签名不一致");
            result = new Result(false,StatusCode.ERROR,"签名不一致");
        } catch (TokenExpiredException e) {
//            e.printStackTrace();
            log.info("令牌过期");
            result = new Result(false,StatusCode.ERROR,"令牌过期");
        } catch (AlgorithmMismatchException e) {
//            e.printStackTrace();
            log.info("失败");
            result = new Result(false,StatusCode.ERROR,"失败");
        } catch (InvalidClaimException e) {
//            e.printStackTrace();
            log.info("失效的payload");
            result = new Result(false,StatusCode.ERROR,"失效的payload");
        } catch (Exception e) {
            log.info("token无效");
//            e.printStackTrace();
            result = new Result(false,StatusCode.ERROR,"token无效");
        }
        //响应到前台: 将map转为json
        String json = new ObjectMapper().writeValueAsString(result);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().println(json);
        return false;
    }
}

