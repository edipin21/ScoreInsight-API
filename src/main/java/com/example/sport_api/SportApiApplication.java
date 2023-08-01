package com.example.sport_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Soccer API", version = "1.0", description = "API documentation"))
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class SportApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(SportApiApplication.class, args);

	}

}
