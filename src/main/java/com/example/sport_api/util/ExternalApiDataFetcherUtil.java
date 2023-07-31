package com.example.sport_api.util;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class ExternalApiDataFetcherUtil {

    private static final Logger logger = LogManager.getLogger(ExternalApiDataFetcherUtil.class);

    Dotenv dotenv = Dotenv.load();

    public String fetchData(String resourceUrl) {

        String apiKey = dotenv.get("SPORT_DATA_IO_API_KEY");

        System.out.println(resourceUrl + apiKey);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + "?key=" +
                apiKey,
                String.class);
        String responsesData = response.getBody();

        return responsesData;
    }

    public ObjectMapper initializeObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        return objectMapper;
    }

    public void handleException(Exception e) {
        if (e instanceof JsonProcessingException) {
            logger.error("Error occurred during JSON processing: {}", e.getMessage(), e);
        } else if (e instanceof IOException) {
            logger.error("Error occurred during I/O operation: {}", e.getMessage(), e);
        } else if (e instanceof DataAccessException) {
            logger.error("Error occurred during data access: {}", e.getMessage(), e);
        }

    }

}
