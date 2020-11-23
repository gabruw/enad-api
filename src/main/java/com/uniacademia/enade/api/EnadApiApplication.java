package com.uniacademia.enade.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EnadApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(EnadApiApplication.class, args);
	}
}
