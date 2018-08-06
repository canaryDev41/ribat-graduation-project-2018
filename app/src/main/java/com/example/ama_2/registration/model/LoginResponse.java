package com.example.ama_2.registration.model;

public class LoginResponse {

    private boolean error;
    private String message;
    private Student student;

    public LoginResponse(Boolean error, String message, Student student) {
        this.error = error;
        this.message = message;
        this.student = student;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Student getStudent() {
        return student;
    }

}
