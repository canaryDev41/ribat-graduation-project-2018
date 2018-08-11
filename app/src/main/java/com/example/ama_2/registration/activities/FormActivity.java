package com.example.ama_2.registration.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.adapters.FormsAdapter;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.FormResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    Button btnReqForum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        int studentID = SharedPrefManager.getInstance(this).getStudent().getId();

        Call<FormResponse> call = ApiClient
                .getInstance()
                .getApi()
                .studentForms(studentID);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("الرحاء الانتظار");
        progressDialog.show();

        btnReqForum = (Button) findViewById(R.id.btnReqForum);
        btnReqForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FormActivity.this, AddFormActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        call.enqueue(new Callback<FormResponse>() {
            @Override
            public void onResponse(Call<FormResponse> call, Response<FormResponse> response) {
                progressDialog.dismiss();
                if (response.body() == null){
                    Toast.makeText(FormActivity.this, "response body is empty", Toast.LENGTH_SHORT).show();
                }else{
                    recyclerView.setAdapter(new FormsAdapter(FormActivity.this, response.body().getForms()));
                }
            }

            @Override
            public void onFailure(Call<FormResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FormActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
