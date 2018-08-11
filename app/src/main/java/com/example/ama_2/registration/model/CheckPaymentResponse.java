package com.example.ama_2.registration.model;

public class CheckPaymentResponse {

    private boolean error;
    private String message;
    private String account_value;
    private String fees;

    public CheckPaymentResponse(Boolean error, String message, String account_value, String fees) {
        this.error = error;
        this.message = message;
        this.account_value = account_value;
        this.fees = fees;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getAccount_value() {
        return account_value;
    }

    public String getFees() {
        return fees;
    }
}
