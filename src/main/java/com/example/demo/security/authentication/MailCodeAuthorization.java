package com.example.demo.security.authentication;

import com.example.demo.common.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class MailCodeAuthorization implements Authentication {

    String mail;
    String code;
    CurrentUser currentUser;
    boolean isAuthenticated = false;

    public MailCodeAuthorization(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }

    @Override
    public String getName() {
        return currentUser.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<GrantedAuthority> of = List.of(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "a";
            }
        });
        return of;
    }

    // get code
    @Override
    public Object getCredentials() {
        return code;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    //get email
    @Override
    public Object getPrincipal() {
        return mail;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

}
