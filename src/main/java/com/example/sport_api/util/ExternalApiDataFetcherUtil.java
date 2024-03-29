package com.example.sport_api.util;

import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class ExternalApiDataFetcherUtil {

    private static final Logger logger = LogManager.getLogger(ExternalApiDataFetcherUtil.class);

    static Dotenv dotenv = Dotenv.load();

    public static String fetchData(String resourceUrl) {

        String apiKey = dotenv.get("SPORT_DATA_IO_API_KEY");

        System.out.println(resourceUrl + apiKey);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + "?key=" +
                apiKey,
                String.class);
        String responsesData = response.getBody();

        return responsesData;
    }

    public static ObjectMapper initializeObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        return objectMapper;
    }

    public static void handleException(Exception e) {
        if (e instanceof JsonProcessingException) {
            logger.error("Error occurred during JSON processing: {}", e.getMessage(), e);
        } else if (e instanceof IOException) {
            logger.error("Error occurred during I/O operation: {}", e.getMessage(), e);
        } else if (e instanceof DataAccessException) {
            logger.error("Error occurred during data access: {}", e.getMessage(), e);
        }

    }

    public static <T> List<T> fetchListDataFromExternalApi(String apiUrl, TypeReference<List<T>> typeReference) {
        try {
            String jsonData = ExternalApiDataFetcherUtil.fetchData(apiUrl);
            ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
            return objectMapper.readValue(jsonData, typeReference);
        } catch (Exception e) {
            handleException(e);
            return null;
        }

    }

    public static <T> T fetchDataFromExternalApi(String apiUrl, Class<T> clazz)
            throws JsonProcessingException {

        String jsonData = ExternalApiDataFetcherUtil.fetchData(apiUrl);
        ObjectMapper objectMapper = ExternalApiDataFetcherUtil.initializeObjectMapper();
        return objectMapper.readValue(jsonData, clazz);
    }

}
