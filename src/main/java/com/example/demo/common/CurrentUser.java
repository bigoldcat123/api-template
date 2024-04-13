package com.example.demo.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//type UserDetail
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser {
    //TODO add other properties 
    
    String id;
    String name;

    public static CurrentUser get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser currentUser = new CurrentUser(authentication.getPrincipal().toString(),authentication.getName());
        return currentUser;
    }
}
