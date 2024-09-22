package com.czh.api.security.safecontroller;

import com.czh.api.common.R;
import com.czh.api.common.mail.MailService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mailcode")
public class MailController {


    @Autowired
    MailService mailService;

    @GetMapping
    public R getMailCode( @Email String email) {
        mailService.sendCode(email);
        return R.okShow("验证码发送成功！");
    }
}
