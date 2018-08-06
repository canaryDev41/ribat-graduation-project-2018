package com.example.ama_2.registration.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.adapters.ExcusesAdapter;
import com.example.ama_2.registration.adapters.ResultDetailsAdapter;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.Excuse;
import com.example.ama_2.registration.model.ExcuseResponse;
import com.example.ama_2.registration.model.ResultResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExcusesDashboardActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    Button btnAddExcuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excuses_dashboard);

        TextView tvType = (TextView) findViewById(R.id.tvType);
        TextView tvFrom = (TextView) findViewById(R.id.tvFrom);
        TextView tvTo = (TextView) findViewById(R.id.tvTo);
        TextView tvStatus = (TextView) findViewById(R.id.tvStatus);

        btnAddExcuse = (Button) findViewById(R.id.btnAddExcuse);
        btnAddExcuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExcusesDashboardActivity.this, AbsencesExcusesActivity.class));
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("تحت المعالجه ...");
        progressDialog.show();

        int studentID = SharedPrefManager.getInstance(this).getStudent().getId();

        Call<ExcuseResponse> call = ApiClient
                .getInstance()
                .getApi()
                .studentExcuses(studentID);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        call.enqueue(new Callback<ExcuseResponse>() {
            @Override
            public void onResponse(Call<ExcuseResponse> call, Response<ExcuseResponse> response) {
                progressDialog.dismiss();
                recyclerView.setAdapter(new ExcusesAdapter(ExcusesDashboardActivity.this, response.body().getExcuses()));
            }

            @Override
            public void onFailure(Call<ExcuseResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ExcusesDashboardActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
