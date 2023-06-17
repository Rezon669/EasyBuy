package com.easybuy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan
@SpringBootApplication

public class EasyBuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyBuyApplication.class, args);
	}

}
