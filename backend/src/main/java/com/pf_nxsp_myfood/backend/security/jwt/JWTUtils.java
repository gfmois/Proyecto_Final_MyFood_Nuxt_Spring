package com.pf_nxsp_myfood.backend.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;

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

    public String encode(String sub, EmployeesTypes type) {
        if (type == null) {
            type = EmployeesTypes.NONE;
        }

        if (sub == null || sub.equals("")) {
            return null;
        }

        Instant exp = Instant.now();
        return Jwts.builder()
                .setSubject(sub)
                .claim("type", type)
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

    public Map<String, ?> getSub(String jwt) {
        try {
            Map<String, Object> returnObj = new HashMap<String, Object>();

            @SuppressWarnings("deprecation")
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();

            returnObj.put("sub", claims.getSubject());
            returnObj.put("type", claims.get("type"));
            
            return returnObj;
        } catch (JwtException e) {
            return null;
        }
    }
}
