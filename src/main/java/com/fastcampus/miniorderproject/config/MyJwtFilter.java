package com.fastcampus.miniorderproject.config;


import com.fastcampus.miniorderproject.model.UserModel;
import com.fastcampus.miniorderproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Component
public class MyJwtFilter extends OncePerRequestFilter {
    @Autowired
    private final MyJwtProvider jwtProvider;
    @Autowired
    private UserService userService;

    public MyJwtFilter(MyJwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("[START] MyJwtFilter doFilterInternal");
        final String authorizationHeader = request.getHeader("Authorization");
        String userID = null;
        String jwtToken = null;
        // 헤더에서 userID 추줄 .
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwtToken = authorizationHeader.substring(7);
            userID = jwtProvider.extractUserId(jwtToken);
        }
        // 조건 검색
        if(userID != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserModel userModel = userService.find(Long.parseLong(userID));
            if( userModel != null && jwtProvider.validateToken(jwtToken,userModel)){
                Authentication authentication = new UsernamePasswordAuthenticationToken(userModel, null, userModel.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        //필터 수행
        filterChain.doFilter(request,response);
    }
}
