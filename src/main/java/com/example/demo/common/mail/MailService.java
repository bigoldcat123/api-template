package com.example.demo.common.mail;

import com.example.demo.common.exception.MailCodeExpiredException;
import com.example.demo.common.exception.MailCodeNotExpiredException;
import com.example.demo.common.exception.MailCodeNotMatchException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class MailService {

    @Value("${spring.mail.username}")
    private String username;

    public static final String REDIS_PREFIX_CODE = "code:";
    public static final String REDIS_PREFIX_MAIL = "mail:";
    public static final String REDIS_CODE_MAIL = REDIS_PREFIX_CODE + REDIS_PREFIX_MAIL;
    public static final int CODE_DURATION_MINUTES = 5;
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;



    private void sendMail(String to, String subject, String content) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(username);
        mailSender.send(simpleMailMessage);
    }

    public void sendCode(String email) {
        Boolean notExpired = redisTemplate.hasKey(REDIS_CODE_MAIL + email);
        if (notExpired) {
            throw new MailCodeNotExpiredException("验证吗未过期，请勿重复获取");
        }
        String code = generateCode(5);
        saveMailCode(code,email);
        try {
            sendMail(email,"Code","确认收款 5289.22$ 验证码 ->{ " + code + " }<- 有效时间 " + CODE_DURATION_MINUTES + "分钟");
        } catch (MessagingException e) {
            throw new MailSendException("邮箱发送失败，请尝试别的方式！😭");
        }
    }

    private void saveMailCode(String code,String mail) {
        log.info("mail code -> {}",code);
        redisTemplate.opsForValue().set(REDIS_CODE_MAIL + mail,code,CODE_DURATION_MINUTES, TimeUnit.MINUTES);
    }

    private String generateCode(int numberOfDigits) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numberOfDigits; i++) {
            // 生成一个 0 到 9 之间的随机数字，并将其转换为字符
            int randomDigit = random.nextInt(10);
            sb.append(randomDigit);
        }

        return sb.toString();
    }

    public void tryAuthenticate(String email, String code)throws AuthenticationException {
        String o = (String) redisTemplate.opsForValue().get(REDIS_CODE_MAIL + email);
        if (o == null) {
            throw new MailCodeExpiredException("验证码过期了 请重新发送！");
        }
        if (!o.equals(code)) {
            throw new MailCodeNotMatchException("验证码不匹配！");
        }
        redisTemplate.delete(REDIS_CODE_MAIL + email);
    }
}
