package com.example.demo.security.provider;

import com.example.demo.common.CurrentUser;
import com.example.demo.common.mail.MailService;
import com.example.demo.security.authentication.MailCodeAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MailCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    MailService mailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MailCodeAuthorization mailCodeAuthorization = (MailCodeAuthorization) authentication;
        String email = (String) mailCodeAuthorization.getPrincipal();
        String code = (String) mailCodeAuthorization.getCredentials();
        mailService.tryAuthenticate(email,code);

        authentication.setAuthenticated(true);
        // TODO find in DB !
        mailCodeAuthorization.setCurrentUser(new CurrentUser(email,"mock name"));
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(MailCodeAuthorization.class);
    }
}
