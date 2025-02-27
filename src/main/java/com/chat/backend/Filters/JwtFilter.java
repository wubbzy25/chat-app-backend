package com.chat.backend.Filters;

import com.chat.backend.Exceptions.JwtTokenMissingException;
import com.chat.backend.Utils.JwtUtils;
import com.chat.backend.Utils.Url_WhiteList;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {



    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver resolver;
    private final JwtUtils jwtUtils;
    private Url_WhiteList url_whiteList;


    public JwtFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, JwtUtils jwtUtils, Url_WhiteList url_whiteList){
        this.resolver = resolver;
        this.jwtUtils = jwtUtils;
        this.url_whiteList = url_whiteList;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (url_whiteList.getUrls().contains(uri)){
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken == null || bearerToken.isEmpty()) {
                throw new JwtTokenMissingException();
            }
            String token = bearerToken.substring( 7);
            if(jwtUtils.validateToken(token)) {
                Authentication authentication = jwtUtils.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);

            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired or invalid");
            }
        } catch (ExpiredJwtException | MalformedJwtException | JwtTokenMissingException e ){
            resolver.resolveException(request, response, null, e);
        }
    }
}
