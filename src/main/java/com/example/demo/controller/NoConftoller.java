package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/no")
public class NoConftoller {
    
    @GetMapping("test")
    public String teString(@RequestParam(required = false) String param) {
        return new String("abc" + param);
    }
}
