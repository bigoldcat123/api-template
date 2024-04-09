package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Configuration
@EnableWebSecurity
public class SafeConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
            }
        };
    }

    @Bean
    public ProviderManager providerManager() throws Exception {
        AuthenticationProvider authenticationManager = new AuthenticationProvider() {
            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }

            @Override
            public Authentication authenticate(
                    Authentication authentication) throws AuthenticationException {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
            }
        };
        ProviderManager p = new ProviderManager(authenticationManager);
        return p;
    }

    /**
     * Disable cross-site request forgery (CSRF) protection.
     * <p>
     * This is needed because the API Gateway does not support CSRF protection.
     *
     * @param httpSecurity the {@link HttpSecurity} to configure
     * @return the {@link SecurityFilterChain}
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(CorsConfigurer::disable)
                .sessionManagement(SessionManagementConfigurer::disable)
                .logout(LogoutConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(config -> config.requestMatchers("/test/**").permitAll()
                        .requestMatchers("/no/**").hasRole("RR")
                        // All other API endpoints require authentication.
                        .anyRequest().authenticated());
        httpSecurity.exceptionHandling(exception -> {
            exception.authenticationEntryPoint((request, response, authException) -> {
                // TODO Auto-generated method stub
                response.setStatus(401);
                response.setContentType("text/plain");
                response.getWriter().write("？exceptionhandler");
            });
            exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                // TODO Auto-generated method stub
                response.setStatus(403);
                response.setContentType("text/plain");
                response.getWriter().write("accessDeniedHandler");
            });
        });
        httpSecurity.addFilterBefore(new Filter() {

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
                    throws IOException, ServletException {
                        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                        emptyContext.setAuthentication(new Authentication() {
                            @Override
                            public String getName() {
                                // TODO Auto-generated method stub
                                return "abc";
                            }

                            @Override
                            public Collection<? extends GrantedAuthority> getAuthorities() {
                                // TODO Auto-generated method stub
                                ArrayList<GrantedAuthority> arrayList = new ArrayList<>();
                                arrayList.add(new GrantedAuthority() {

                                    @Override
                                    public String getAuthority() {
                                        // TODO Auto-generated method stub
                                        return "ROLE_RR";
                                    }
                                });
                                return arrayList;
                            }

                            @Override
                            public Object getCredentials() {
                                // TODO Auto-generated method stub
                                return "abc";
                            }

                            @Override
                            public Object getDetails() {
                                // TODO Auto-generated method stub
                                return "abc";
                            }

                            @Override
                            public Object getPrincipal() {
                                // TODO Auto-generated method stub
                                return "abc";
                            }

                            @Override
                            public boolean isAuthenticated() {
                                // TODO Auto-generated method stub
                                return true;
                            }

                            @Override
                            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                                // TODO Auto-generated method stub
                                throw new UnsupportedOperationException("Unimplemented method 'setAuthenticated'");
                            }
                        });
                        SecurityContextHolder.setContext(emptyContext);
                        System.out.println("验证 TOKEN。");
                        filterChain.doFilter(request, response);
            }
            
        }, UsernamePasswordAuthenticationFilter.class);
        DefaultSecurityFilterChain defaultSecurityFilterChain = httpSecurity.build();
        return defaultSecurityFilterChain;
    }
}
