package com.pet.demo.exception;

public class LatestDateNotFoundException extends RuntimeException {
    public LatestDateNotFoundException() {
        super(String.format("Latest date found"));
    }
}
