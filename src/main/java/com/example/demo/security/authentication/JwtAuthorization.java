package com.example.demo.security.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.example.demo.common.CurrentUser;

public class JwtAuthorization implements Authentication {

    String token;
    CurrentUser currentUser;
    boolean isAuthenticated = false;

    public JwtAuthorization(String token) {
        this.token = token;
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

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return currentUser.getId();
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
