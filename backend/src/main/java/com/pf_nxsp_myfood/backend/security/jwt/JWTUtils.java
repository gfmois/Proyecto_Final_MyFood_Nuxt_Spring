package com.pf_nxsp_myfood.backend.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import com.pf_nxsp_myfood.backend.plugins.IdGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUtils {
    private final Long validSeconds;
    private final Key key;

    public JWTUtils(String signKey, Long validSeconds) {
        this.validSeconds = validSeconds;
        key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
    }

    public String encode(String sub, Boolean isAdmin) {
        if (isAdmin == null) {
            isAdmin = false;
        }


        if (sub == null || sub.equals("")) {
            return null;
        }

        Instant exp = Instant.now();
        return Jwts.builder()
                .setSubject(isAdmin 
                    ? String.format("%s_%s", sub, IdGenerator.generateWithLength(5)) 
                    : sub)
                .setIssuedAt(new Date(exp.toEpochMilli()))
                .setExpiration(new Date(
                        exp.toEpochMilli()
                                + validSeconds
                                * 1000))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            @SuppressWarnings("deprecation")
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            Instant now = Instant.now();
            Date exp = claims.getExpiration();
            return exp.after(Date.from(now));
        } catch (JwtException e) {
            return false;
        }
    }

    public String getSub(String jwt) {
        try {
            @SuppressWarnings("deprecation")
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
