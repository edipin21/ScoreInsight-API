package com.example.sport_api.services.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sport_api.repositories.users.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean isValidApiKey(String apiKey) {
        List<String> apiKeys = userRepository.findAllApiKeys();

        return apiKeys.contains(apiKey);
    }

}
