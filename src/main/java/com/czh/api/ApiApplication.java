package com.czh.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//撒打算大
//another

@SpringBootApplication
@Slf4j
@MapperScan("com.czh.api.system.mapper")
public class ApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ApiApplication.class, args);
	}

}
