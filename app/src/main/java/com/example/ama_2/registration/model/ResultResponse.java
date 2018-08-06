package com.example.ama_2.registration.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultResponse {

    @SerializedName("result")
    private Result result;

    @SerializedName("details")
    private ArrayList<ResultDetails> details;

    private boolean error;

    public Result getResult() {
        return result;
    }

    public boolean isError() {
        return error;
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
