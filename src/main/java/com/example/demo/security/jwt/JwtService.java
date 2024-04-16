package com.example.demo.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import com.alibaba.fastjson2.JSON;
import com.example.demo.common.CurrentUser;

import io.jsonwebtoken.Jwts;

public class JwtService {

    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public static String createToken(CurrentUser currentUser) {
        String compact = Jwts.builder()
                .subject(JSON.toJSONString(currentUser))
                .signWith(secretKey)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
        return compact;
    }

    public static CurrentUser parseToken(String token) {
        CurrentUser currentUser = JSON.parseObject(Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload().getSubject(), CurrentUser.class);
        return currentUser;
    }
    public static void main(String[] args) {
        String token = JwtService.createToken(new CurrentUser("1", "2"));
        CurrentUser token2 = JwtService.parseToken(token);
        System.out.println(token2);
    }
}
