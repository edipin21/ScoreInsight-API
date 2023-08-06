package com.example.sport_api.util.userUtil;

import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyGenerator {
    public static String generateRandomApiKey() {
        SecureRandom random = new SecureRandom();
        byte[] apiKeyBytes = new byte[16];
        random.nextBytes(apiKeyBytes);
        return Base64.getEncoder().encodeToString(apiKeyBytes);
    }
}
