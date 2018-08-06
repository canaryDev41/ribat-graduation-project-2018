package com.example.ama_2.registration.model;

import com.google.gson.annotations.SerializedName;

public class ResultDetails {

    @SerializedName("id")
    private String id;

    @SerializedName("result_id")
    private String result_id;

    @SerializedName("course_name")
    private String course_name;

    @SerializedName("course_appreciation")
    private String course_appreciation;

    @SerializedName("course_points")
    private String course_points;

    public ResultDetails(String id, String result_id, String course_name, String course_appreciation, String course_points) {
        this.id = id;
        this.result_id = result_id;
        this.course_name = course_name;
        this.course_appreciation = course_appreciation;
        this.course_points = course_points;
    }

    public String getId ()
    {
        return id;
    }

    public String getResult_id ()
    {
        return result_id;
    }

    public String getCourse_name ()
    {
        return course_name;
    }

    public String getCourse_appreciation ()
    {
        return course_appreciation;
    }

    public String getCourse_points ()
    {
        return course_points;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", result_id = "+result_id+", course_name = "+course_name+", course_appreciation = "+course_appreciation+", course_points = "+course_points+"]";
    }

}
