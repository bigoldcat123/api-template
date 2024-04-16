package com.example.demo.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson2.JSON;
import com.example.demo.common.R;
import com.example.demo.security.JwtAuthorizationManager;
import com.example.demo.security.authentication.JwtAuthorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutFilter extends OncePerRequestFilter {
    @Autowired
    JwtAuthorizationManager jwtAuthorizationManager;
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/logout",
            "POST");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (DEFAULT_ANT_PATH_REQUEST_MATCHER.matches(request)) {
            String jwttokenString = request.getHeader("Authorization");
            if (jwttokenString != null && jwttokenString.startsWith("Bearer ")) {
//                jwttokenString = jwttokenString.replace("Bearer ", "");
//                JwtAuthorization jwtAuthorization = new JwtAuthorization(jwttokenString);
//                jwtAuthorizationManager.verify(() -> jwtAuthorization, null);
                // TODO after logout you have to do something such as websocket connection.
                logger.error("没有清理WebSocket连接！！");
                response.setStatus(200);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(R.okShow(R.SHOW_SUCCESS, "退出成功")));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
