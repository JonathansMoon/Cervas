package com.cervas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.cervas.repository")
@SpringBootApplication
public class CervasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CervasApplication.class, args);
	}

}
