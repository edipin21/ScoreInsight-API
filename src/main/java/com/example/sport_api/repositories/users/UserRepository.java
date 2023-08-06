package com.example.sport_api.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sport_api.models.users.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT apiKey FROM user", nativeQuery = true)
    List<String> findAllApiKeys();
}
