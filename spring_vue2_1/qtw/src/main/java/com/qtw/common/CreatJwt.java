package com.qtw.common;

import com.qtw.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreatJwt {
    public static String getoken(User user){
        // Jwts.builder();//生成
        // Jwts.parser();//解密

        JwtBuilder jwtBuilder=Jwts.builder()
                .setId(user.getId()+"") //设置需要加密的内容
                .setSubject(user.getNo())
                .setIssuedAt(new Date())//token保留时间
                .signWith(SignatureAlgorithm.HS256,"my-123").setExpiration(
                        new Date(new Date().getTime()+86400000)
                );
        System.out.println(jwtBuilder.compact());
        return jwtBuilder.compact();
    }
}