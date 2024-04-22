package com.example.demo.security.config;


import com.example.demo.security.filter.MailCodeAuthenticationFilter;
import com.example.demo.security.provider.MailCodeAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.filter.CommonUserNamePasswordAuthenticationFilter;
import com.example.demo.security.filter.JwtAuthorizationFilter;
import com.example.demo.security.filter.LogoutFilter;
import com.example.demo.security.hadnler.AccessDenyExceptionHandler;
import com.example.demo.security.hadnler.AuthenticationExceptionHandler;
import com.example.demo.security.provider.MyDaoAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    String [] writeList = {"/ws","/mailcode"};
    @Bean
    public AuthenticationManager authenticationManager(
            MyDaoAuthenticationProvider myDaoAuthenticationProvider,
            MailCodeAuthenticationProvider mailCodeAuthenticationProvider
    ) throws Exception {
        ProviderManager p = new ProviderManager(
                myDaoAuthenticationProvider,
                mailCodeAuthenticationProvider);
        return p;
    }
    @Bean
    public SecurityFilterChain configureSecurityFilterChain(
        HttpSecurity httpSecurity,
        CommonUserNamePasswordAuthenticationFilter userNamePasswordAuthenticationFilter,
        JwtAuthorizationFilter jwtAuthorizationFilter,
        MailCodeAuthenticationFilter mailCodeAuthenticationFilter,
        LogoutFilter logoutFilter) throws Exception {
            httpSecurity.cors(CorsConfigurer::disable)
                    .sessionManagement(SessionManagementConfigurer::disable)
                    .logout(LogoutConfigurer::disable)
                    .formLogin(FormLoginConfigurer::disable)
                    .csrf(CsrfConfigurer::disable)
                    .authorizeHttpRequests(config -> config.requestMatchers(writeList).permitAll()
                            // .requestMatchers("/no/**").hasRole("RR")
                            // All other API endpoints require authentication.
                            .anyRequest().authenticated());

            httpSecurity.exceptionHandling(exception -> {
                exception.authenticationEntryPoint(new AuthenticationExceptionHandler());
                exception.accessDeniedHandler(new AccessDenyExceptionHandler());
            });

            httpSecurity.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
            httpSecurity.addFilterBefore(userNamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            httpSecurity.addFilterBefore(logoutFilter, JwtAuthorizationFilter.class);
            httpSecurity.addFilterBefore(mailCodeAuthenticationFilter, JwtAuthorizationFilter.class);

            DefaultSecurityFilterChain defaultSecurityFilterChain = httpSecurity.build();
            return defaultSecurityFilterChain;
    }
}
