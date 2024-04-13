package com.example.demo.common;

import lombok.Data;

// type CurrentUser
@Data
public class CurrentUserVo {
    //TODO add other properties 
    CurrentUser detail;
    String token;
    public CurrentUserVo(CurrentUser currentUser, String token) {
        this.detail = currentUser;
        this.token = token;
    }
}
