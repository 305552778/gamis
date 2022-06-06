package com.xi.gamis.infrastructure.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {
    private long tokenExpiration=24*60*60*1000;
    private String key="12345";

    public String createToken(String username)
    {
        //使用JWT根据用户名生成TOKEN
          String token= "";
          token=Jwts.builder().setExpiration(new Date(System.currentTimeMillis()+tokenExpiration))
                  .setSubject(username)
                  .signWith(SignatureAlgorithm.HS512,key)
                  .compressWith(CompressionCodecs.GZIP)
                  .compact();
          return token;
    }
    //更具token得到用户信息
    public String getUsrInforFromToken(String token)
    {
       return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }
    //删除token
    public void removeToken(String token)
    {

    }
}
