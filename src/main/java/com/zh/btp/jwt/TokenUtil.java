package com.zh.btp.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zh.btp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class TokenUtil {

    //密钥
    public static final String SECRET = "youareapig??shabixiangpojie?";
    //过期时间:秒
    public static final int EXPIRE = 1800;
    /**
     * 生成Token
     */
    public static String createToken(User user){
        Calendar nowTime = Calendar.getInstance();
        //过期时间
        nowTime.add(Calendar.SECOND, EXPIRE);
        Date expireDate = nowTime.getTime();
        String token = JWT.create()
        		//这是在设置第二部分信息，不要设置密码之类的，因为这些信息可以通过浏览器获取
        		//用户id
                .withClaim("id", user.getId())
                //用户名
                .withClaim("username",user.getUsername())
                //创建token的时间
                .withIssuedAt(new Date())//签名时间
                //设置token的过期时间
                .withExpiresAt(expireDate)//过期时间
                //设置第一部分
                .sign(Algorithm.HMAC256(SECRET));//签名
        return token;
    }

    /**
     * 验证token
     */
    public static DecodedJWT verify(String token) {
        //如果有任何验证异常，此处都会抛出异常 我们需要在拦截器调用这个方法，捕获异常，然后返回错误信息给前端
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println(decodedJWT);
        return decodedJWT;
    }

    /**
     * 获取token中的 payload 也就是第二部分的信息
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        //使用 TokenUtils.getTokenInfo(token).getClaim("account").asString()
        return decodedJWT;
    }
}

