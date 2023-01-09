package com.szw.commonweal.utils;

import io.jsonwebtoken.*;


import java.util.Date;
import java.util.UUID;

public class TokenDecrypt {
    //计算一天的毫秒数
    private static long time = 1000*60*60*24;
    //签名信息
    private static String signature = "admin";

    public static String createToken(){
        JwtBuilder jwtBulider= Jwts.builder();
        String jwtToken=jwtBulider
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //添加载荷
                .claim("username","admin")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //签名
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.printf(jwtToken);
        return jwtToken;
    }

    public void parse(){
        String TOKEN="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2NzI5NDU4OTUsImp0aSI6ImQwMzQyNGJhLWMxMDYtNDgwZC05ZmRlLWVhOTBjY2Q0MGE4YyJ9.ZfVSiQwO-1Rk-ccLDazLbjGzNm8n7-4Q3fb5IM8jObw";
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(TOKEN);
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
    }

    public static boolean checkToken(String token){
        if (token==null){
            return false;
        }else {
            try {
                Jwt<Header, Claims> claimsJwt = Jwts.parser().setSigningKey(signature).parseClaimsJwt(token);
            }catch (Exception e){
                return false;
            }
            return true;
        }
    }
}

