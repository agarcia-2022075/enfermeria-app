package com.enfermeria.enfermeria_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

// @ConfigurationPropertiesScan(basePackages = "com.enfermeria.enfermeria_app") // opcional
public class EnfermeriaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnfermeriaAppApplication.class, args);
	}
}