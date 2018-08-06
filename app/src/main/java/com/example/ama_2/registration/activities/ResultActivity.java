package com.example.ama_2.registration.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.adapters.ResultDetailsAdapter;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.LoginResponse;
import com.example.ama_2.registration.model.Result;
import com.example.ama_2.registration.model.ResultDetails;
import com.example.ama_2.registration.model.ResultResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    private TextView tvGpa, tvAcademicDecision;
    private ProgressDialog progressDialog;
    private ArrayList<ResultResponse> resultList;
    public ArrayList<ResultDetails> resultDetailsList;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvGpa = (TextView)findViewById(R.id.tvGpa);
        tvAcademicDecision = (TextView)findViewById(R.id.tvAcademicDecision);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري المعالجه");
        progressDialog.show();


        int studentID = SharedPrefManager.getInstance(this).getStudent().getId();

        Call<ResultResponse> call = ApiClient
                .getInstance()
                .getApi()
                .studentResult(studentID);


        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {

                progressDialog.dismiss();

                if  (response.body().isError()){
                    Toast.makeText(ResultActivity.this, "Sorry There is an error", Toast.LENGTH_SHORT).show();
                }else{

                    tvGpa.setText(response.body().getResult().getGpa());
                    tvAcademicDecision.setText(response.body().getResult().getAcademic_decision());

                    recyclerView.setAdapter(new ResultDetailsAdapter(ResultActivity.this, response.body().getResult().getResultDetails()));

                }

            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

                progressDialog.dismiss();

                Toast.makeText(ResultActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


}
