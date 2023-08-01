package com.example.sport_api.controllers.soccer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sport_api.util.ResponseUtil;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@Hidden
public class UnknownURLController {

    @GetMapping("/scores/**")
    public ResponseEntity<?> handleUnknownURL(HttpServletRequest request) {
        return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "");
    }

}