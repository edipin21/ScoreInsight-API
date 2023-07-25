package com.example.sport_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan("com.example.sport_api.models.sport")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class SportApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(SportApiApplication.class, args);

	}

}
