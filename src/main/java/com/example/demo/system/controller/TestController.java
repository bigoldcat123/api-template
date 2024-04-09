package com.example.demo.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping("test")
    public String teString(@RequestParam(required = false) String param) {
        return new String("abc");
    }
    
}
