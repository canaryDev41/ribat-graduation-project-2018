package com.example.ama_2.registration.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {

    private String id;

    private ArrayList<ResultDetails> details;

    private String gpa;

    private String student_id;

    private String academic_decision;

    public Result(String id, ArrayList<ResultDetails> details, String gpa, String student_id, String academic_decision) {
        this.id = id;
        this.details = details;
        this.gpa = gpa;
        this.student_id = student_id;
        this.academic_decision = academic_decision;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public ArrayList<ResultDetails> getResultDetails ()
    {
        return details;
    }

    public void setResultDetails (ArrayList<ResultDetails> details)
    {
        this.details = details;
    }

    public String getGpa ()
    {
        return gpa;
    }

    public void setGpa (String gpa)
    {
        this.gpa = gpa;
    }

    public String getStudent_id ()
    {
        return student_id;
    }

    public void setStudent_id (String student_id)
    {
        this.student_id = student_id;
    }

    public String getAcademic_decision ()
    {
        return academic_decision;
    }

    public void setAcademic_decision (String academic_decision)
    {
        this.academic_decision = academic_decision;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", details = "+details+", gpa = "+gpa+", student_id = "+student_id+", academic_decision = "+academic_decision+"]";
    }
}
