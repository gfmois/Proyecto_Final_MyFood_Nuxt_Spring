package com.pf_nxsp_myfood.backend.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pf_nxsp_myfood.backend.security.AuthenticationProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends GenericFilter {
    public static final String TOKEN_PREFIX = "Bearer";
    private final JWTUtils jwtUtils;
    private final AuthenticationProvider authenticationProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional.ofNullable(((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith(TOKEN_PREFIX))
                .map(authHeader -> authHeader.substring(TOKEN_PREFIX.length()))
                .filter(jwtUtils::validateToken)
                .map(jwtUtils::getSub)
                .map(authenticationProvider::getAuthentication)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);

//        if (((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION) == null) {
//            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The Token is Not valid");
//        }

        chain.doFilter(request, response);
    }
}