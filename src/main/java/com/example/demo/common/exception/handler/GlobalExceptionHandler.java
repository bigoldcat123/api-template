package com.example.demo.common.exception.handler;

import com.example.demo.common.R;
import com.example.demo.common.exception.MailCodeNotExpiredException;
import com.example.demo.common.exception.MailSendFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        System.out.println(e.getClass());
        log.error(e.getMessage());
        return R.errorShow("服务器裂开啦～😣");
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public R handleException(HandlerMethodValidationException e) {
        return R.errorShow("请输入正确的格式");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return R.errorShow("格式错误");
    }
    @ExceptionHandler(MailCodeNotExpiredException.class)
    public R handleMailNotExpiredException(MailCodeNotExpiredException e) {
        return R.errorShow(e.getMessage());
    }
    @ExceptionHandler(MailSendFailedException.class)
    public R handleMailSendFailedException(MailSendFailedException e) {
        return R.errorShow(e.getMessage());
    }
}
