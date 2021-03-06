package com.example.ama_2.registration.api;

import com.example.ama_2.registration.model.CheckPaymentResponse;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.model.FormResponse;
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

    @GET("student/{stdID}/forms")
    Call<FormResponse> studentForms(
            @Path("stdID") int stdID
    );

    @FormUrlEncoded
    @POST("student/form")
    Call<DefaultResponse> studentForm(
            @Field("type") String type,
            @Field("student_id") int student_id
    );

    @Multipart
    @POST("student/{stdID}/excuse")
    Call<ResponseBody> addExcuse(
            @Part("type") RequestBody type,
            @Part("note") RequestBody note,
            @Part MultipartBody.Part attach
    );

    @GET("registration/{stdID}/check")
    Call<DefaultResponse> registrationCheck(
            @Path("stdID") int stdID
    );

    @GET("payment/{stdID}/check")
    Call<CheckPaymentResponse> paymentCheck(
            @Path("stdID") int stdID
    );

    @FormUrlEncoded
    @POST("registration/payment")
    Call<DefaultResponse> payment(
            @Field("student_id") int student_id
    );

    @FormUrlEncoded
    @POST("register")
    Call<DefaultResponse> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("stdID") String stdID,
            @Field("password") String password,
            @Field("acceptance_year") String acceptance_year,
            @Field("department_id") String department_id
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> studentLogin(
            @Field("stdID") String stdID,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registration")
    Call<DefaultResponse> registration(
            @Field("student_id") int student_id,
            @Field("semester") String semester,
            @Field("level") String level,
            @Field("blood") String blood,
            @Field("phone") String phone,
            @Field("parentName") String parentName,
            @Field("parentJob") String parentJob,
            @Field("parentPhone") String parentPhone,
            @Field("nationality") String nationality,
            @Field("religion") String religion
    );
}