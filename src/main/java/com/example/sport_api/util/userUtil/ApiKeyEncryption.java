package com.example.sport_api.util.userUtil;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import io.github.cdimascio.dotenv.Dotenv;

public class ApiKeyEncryption {

    static Dotenv dotenv = Dotenv.load();

    public static String encryptApiKey(String apiKey) throws Exception {
        Cipher cipher = Cipher.getInstance(dotenv.get("API_ENCRYPTION_ALGORITHM"));
        SecretKeySpec keySpec = new SecretKeySpec(
                dotenv.get("API_SECRET_KEY").getBytes(StandardCharsets.UTF_8),
                dotenv.get("API_ENCRYPTION_ALGORITHM"));
        System.out.println(keySpec.getEncoded().length);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(apiKey.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptApiKey(String encryptedApiKey) throws Exception {
        Cipher cipher = Cipher.getInstance(dotenv.get("API_ENCRYPTION_ALGORITHM"));
        SecretKeySpec keySpec = new SecretKeySpec(dotenv.get("API_SECRET_KEY").getBytes(StandardCharsets.UTF_8),
                dotenv.get("API_ENCRYPTION_ALGORITHM"));
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedApiKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
