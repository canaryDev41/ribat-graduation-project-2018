package com.example.ama_2.registration.model;

import com.google.gson.annotations.SerializedName;

public class Student{

    @SerializedName("id")
    private int Id;

    @SerializedName("name")
    private String Name;

    @SerializedName("email")
    private String Email;

    @SerializedName("stdID")
    private String StdID;

    @SerializedName("phone")
    private String Phone;

    public Student(int id, String name, String email, String stdID, String phone) {
        Id = id;
        Name = name;
        Email = email;
        StdID = stdID;
        Phone = phone;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getStdID() {
        return StdID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setStdID(String stdID) {
        StdID = stdID;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
