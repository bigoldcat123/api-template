package com.example.demo.security;

import java.util.function.Supplier;

import org.springframework.lang.Nullable;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.common.CurrentUser;
import com.example.demo.security.authentication.JwtAuthorization;
import com.example.demo.security.jwt.JwtService;

@Component
public class JwtAuthorizationManager implements AuthorizationManager<Object>{

    @Override
    @Nullable
    public AuthorizationDecision check(Supplier<Authentication> authentication,@Nullable Object object) {
        AuthorizationDecision authorizationDecision;
        JwtAuthorization jwtAuthorization = (JwtAuthorization)authentication.get();
        try {
            CurrentUser currentUser = JwtService.parseToken(jwtAuthorization.getCredentials().toString());
            jwtAuthorization.setCurrentUser(currentUser);
            jwtAuthorization.setAuthenticated(true);
            SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
            emptyContext.setAuthentication(jwtAuthorization);
            SecurityContextHolder.setContext(emptyContext);
            authorizationDecision = new AuthorizationDecision(true);
        } catch (Exception e) {
            authorizationDecision = new AuthorizationDecision(false);
        }
        return authorizationDecision;
    }
    
}
