package com.czh.api.security;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.czh.api.common.CurrentUser;
import com.czh.api.security.authentication.JwtAuthorization;
import com.czh.api.security.jwt.JwtService;

@Component
public class JwtAuthorizationManager implements AuthorizationManager<Object>{


    @Value("${spring.profiles.active}")
    String env;
    @Value("${devToken}")
    String devToken;

    @Override
    @Nullable
    public AuthorizationDecision check(Supplier<Authentication> authentication,@Nullable Object object) {
        AuthorizationDecision authorizationDecision;
        JwtAuthorization jwtAuthorization = (JwtAuthorization)authentication.get();

        if(env.equals("dev") && jwtAuthorization.getCredentials().equals(devToken)) {
            jwtAuthorization.setAuthenticated(true);
            jwtAuthorization.setCurrentUser(new CurrentUser(1,"admin","email"));
            SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
            emptyContext.setAuthentication(jwtAuthorization);
            SecurityContextHolder.setContext(emptyContext);
            return new AuthorizationDecision(true);
        }

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
