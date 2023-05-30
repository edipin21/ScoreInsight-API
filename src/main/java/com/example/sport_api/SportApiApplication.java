package com.example.sport_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

@SpringBootApplication
@EnableScheduling
public class SportApiApplication {
	private static final Logger logger = LogManager.getLogger(SportApiApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SportApiApplication.class, args);
		logger.debug("yaaaaaaaa raqbbiiiiii");
	}

}
