package com.example.ama_2.registration.api;

import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.model.LoginResponse;
import com.example.ama_2.registration.model.RegistrationResponse;
import com.example.ama_2.registration.model.ResultResponse;
import com.example.ama_2.registration.model.ExcuseResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("student/{stdID}/result")
    Call<ResultResponse> studentResult(
            @Path("stdID") int stdID
    );

    @GET("student/{stdID}/excuses")
    Call<ExcuseResponse> studentExcuses(
            @Path("stdID") int stdID
    );

    @Multipart
    @POST("student/{stdID}/excuse")
    Call<ResponseBody> addExcuse(
            @Part("type") RequestBody type,
            @Part("note") RequestBody note,
            @Part MultipartBody.Part attach
    );

    @FormUrlEncoded
    @POST("register")
    Call<DefaultResponse> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("stdID") String stdID,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> studentLogin(
            @Field("stdID") String stdID,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registration")
    Call<RegistrationResponse> registration(
            @Field("student_id") int student_id,
            @Field("semester") String semester,
            @Field("level") String level,
            @Field("blood") String blood,
            @Field("phone") String phone,
            @Field("parent_name") String parentName,
            @Field("parent_job") String parentJob,
            @Field("parent_phone") String parentPhone,
            @Field("nationality") String nationality,
            @Field("religion") String religion
    );
}