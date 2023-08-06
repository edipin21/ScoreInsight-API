package com.example.sport_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport_api.models.users.User;
import com.example.sport_api.repositories.users.UserRepository;
import com.example.sport_api.util.userUtil.ApiKeyEncryption;
import com.example.sport_api.util.userUtil.ApiKeyGenerator;

import io.swagger.v3.oas.annotations.Hidden;

// use until the website will be ready and change the encryption 
@RestController
@Hidden
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/{name}/{email}")
    public void createUser(@PathVariable String name, @PathVariable String email) throws Exception {

        String apiKey = ApiKeyGenerator.generateRandomApiKey();

        String encryptedApiKey = ApiKeyEncryption.encryptApiKey(apiKey);

        User user = new User(1, name, email, "123", encryptedApiKey);

        userRepository.save(user);
    }

}
