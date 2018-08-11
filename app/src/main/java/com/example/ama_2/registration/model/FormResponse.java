package com.example.ama_2.registration.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FormResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("forms")
    private ArrayList<Form> forms;

    public boolean isError() {
        return error;
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

}
