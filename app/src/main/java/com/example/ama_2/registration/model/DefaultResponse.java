package com.example.ama_2.registration.model;

public class DefaultResponse {

    private boolean error;
    private String message;

    public DefaultResponse(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
