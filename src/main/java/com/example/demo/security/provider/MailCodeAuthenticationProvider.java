package com.example.demo.security.provider;

import com.example.demo.common.CurrentUser;
import com.example.demo.common.mail.MailService;
import com.example.demo.security.authentication.MailCodeAuthorization;
import com.example.demo.system.entity.UserAuth;
import com.example.demo.system.service.IUserAuthService;
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
    IUserAuthService userAuthService;
    @Autowired
    MailService mailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MailCodeAuthorization mailCodeAuthorization = (MailCodeAuthorization) authentication;
        String email = (String) mailCodeAuthorization.getPrincipal();
        String code = (String) mailCodeAuthorization.getCredentials();

        mailService.tryAuthenticate(email,code);

        authentication.setAuthenticated(true);
        UserAuth userAuthByEmail = userAuthService.getUserAuthByEmail(email);
        mailCodeAuthorization.setCurrentUser(new CurrentUser(userAuthByEmail.getId(),userAuthByEmail.getUsername(),userAuthByEmail.getEmail()));
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(MailCodeAuthorization.class);
    }
}
