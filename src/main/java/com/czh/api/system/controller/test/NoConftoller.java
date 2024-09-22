package com.czh.api.system.controller.test;

import com.czh.api.common.R;
import com.czh.api.system.entity.test.TestValifacationEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.czh.api.common.CurrentUser;

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
