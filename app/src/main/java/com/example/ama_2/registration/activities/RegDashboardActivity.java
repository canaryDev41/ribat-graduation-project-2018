package com.example.ama_2.registration.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    CardView cvRegStatus;
    TextView tvRegistered, tvUnRegistered;
    Button btnRegister, btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_dashboard);

        cvRegStatus = (CardView) findViewById(R.id.cvRegStatus);
        tvRegistered = (TextView) findViewById(R.id.tvRegistered);
        tvUnRegistered = (TextView) findViewById(R.id.tvUnRegistered);
        btnPay = (Button) findViewById(R.id.btnPay);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnPay.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading ...");

        check();
    }

    public void check(){
        int stdID = SharedPrefManager.getInstance(this).getStudent().getId();

        progressDialog.show();

        Call<DefaultResponse> call = ApiClient.getInstance().getApi().registrationCheck(stdID);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                progressDialog.dismiss();

                if (response.body().isError()){
                    cvRegStatus.setVisibility(View.VISIBLE);
                    tvUnRegistered.setVisibility(View.VISIBLE);
                    btnRegister.setVisibility(View.VISIBLE);
                }else{
                    cvRegStatus.setVisibility(View.VISIBLE);
                    tvRegistered.setVisibility(View.VISIBLE);
                    btnPay.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegDashboardActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPay:
                startActivity(new Intent(this, PaymentActivity.class));
                break;

            case R.id.btnRegister:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
        }
    }
}
