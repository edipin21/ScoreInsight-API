package com.example.sport_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories(basePackages = "com.example.sport_api.repositories", entityManagerFactoryRef = "entityManagerFactory")
@SpringBootApplication
@EnableScheduling
public class SportApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(SportApiApplication.class, args);

	}

}
