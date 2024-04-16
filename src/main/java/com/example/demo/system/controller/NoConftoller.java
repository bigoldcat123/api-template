package com.example.demo.system.controller;

import com.example.demo.common.R;
import com.example.demo.system.controller.entity.TestValifacationEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.common.CurrentUser;

@RestController
@RequestMapping("/no")

public class NoConftoller {
    
    @GetMapping("test")
    public String teString(String param) {
        System.out.println(CurrentUser.get());

        return new String("abc" + param);
    }
    @PostMapping
    public R r(@RequestBody@Validated TestValifacationEntity entity) {
        System.out.println(entity);
        return R.ok();
    }
}
