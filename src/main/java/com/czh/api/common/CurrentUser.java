package com.czh.api.common;

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
    
    Integer id;
    String username;
    String email;

    public static CurrentUser get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CurrentUser) authentication.getDetails();
    }
}
