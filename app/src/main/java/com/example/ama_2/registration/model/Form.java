package com.example.ama_2.registration.model;

public class Form {

    private String type, date, status;

    public Form(String type, String date, String status) {
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
