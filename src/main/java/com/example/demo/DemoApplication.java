package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication {

	@GetMapping("path")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	


	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		log.debug("abc");
		log.info("xyz");
		log.error("eee");
		System.out.println("start!");
	}

}
