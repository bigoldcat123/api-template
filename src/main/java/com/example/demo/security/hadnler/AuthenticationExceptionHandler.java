package com.example.demo.security.hadnler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.alibaba.fastjson2.JSON;
import com.example.demo.common.R;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationExceptionHandler implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
                response.setStatus(200);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(R.errorShow("请先登录！")));
    }

}
