package com.example.sport_api.models.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    int userId;
    String name;
    String email;
    String password;
    String apiKey;

    public User() {
    }

    public User(int userId, String name, String email, String password, String apiKey) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.apiKey = apiKey;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
