package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String name;
    private String password;

    private String doctor;

    // Getters and Setters
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
