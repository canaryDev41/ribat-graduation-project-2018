package com.example.ama_2.registration.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ExcuseResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("excuses")
    private ArrayList<Excuse> excuses;

    public boolean isError() {
        return error;
    }

    public ArrayList<Excuse> getExcuses() {
        return excuses;
    }

}
