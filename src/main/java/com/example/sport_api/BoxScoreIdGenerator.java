package com.example.sport_api;

public class BoxScoreIdGenerator {
    private static Integer nextId = 1;

    public static synchronized Integer generateId() {
        return nextId++;
    }

}
