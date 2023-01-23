package com.szw.commonweal.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SIGN = "SZW";

    public static String setToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        JWTCreator.Builder bulider = JWT.create();
        map.forEach((k, v) -> {
            bulider.withClaim(k, v);
        });

        String token = bulider.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        System.out.println(token);
        return token;

    }

    public static  Map<String, String> verify(String token) {
        Map<String, String> map = new HashMap<>();
        try {
            JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        } catch (SignatureVerificationException e) {
            map.put("msg", "无效签名");
            e.printStackTrace();
        } catch (TokenExpiredException e) {
            map.put("msg", "TOKEN过期");
            e.printStackTrace();
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "算法异常");
            e.printStackTrace();
        } catch (Exception e) {
            map.put("msg", "token异常");
            e.printStackTrace();
        }
        return map;
    }

}