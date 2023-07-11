package com.example.sport_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.example.sport_api.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UnknownURLController {

    @GetMapping("/scores/**")
    public ResponseEntity<String> handleUnknownURL(HttpServletRequest request) {
        return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "");
    }

}