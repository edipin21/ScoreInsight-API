package com.example.sport_api.util;

import java.util.Map;
import java.util.LinkedHashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseUtil {

    // @Autowired
    // private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> ResponseEntity<T> createOkResponse(T body) {

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        // headers.add(HttpHeaders.CONTENT_LENGTH,
        // String.valueOf(calculateContentLength(body)));
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.VIA, "1.1 varnish");
        headers.add(HttpHeaders.ACCEPT_RANGES, "bytes");
        headers.add(HttpHeaders.DATE, ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        headers.add(HttpHeaders.AGE, null);
        headers.add(HttpHeaders.VARY, "Accept-Encoding");
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> createErrorResponse(HttpStatus status, String description) {
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_RANGES, "bytes");
        headers.add(HttpHeaders.VIA, "1.1 varnish");
        headers.add(HttpHeaders.DATE, ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        Map<String, Object> responseBody = new LinkedHashMap<>();
        if (status != HttpStatus.NOT_FOUND) {
            responseBody.put("HttpStatusCode", status.value());
            responseBody.put("Code", status.value());
            responseBody.put("Description", description);
            responseBody.put("Help", "Please contact support@eddie.sportdata for assistance");
        } else {
            responseBody.put("statusCode", status.value());
            responseBody.put("message", "Resource not found");
        }

        return new ResponseEntity<>((T) responseBody, headers, status);

    }

}
