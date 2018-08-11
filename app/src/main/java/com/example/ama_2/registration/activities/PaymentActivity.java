package com.example.ama_2.registration.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.CheckPaymentResponse;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    CardView cvPaymentStatus;
    TextView tvPaid, tvUnPaid;
    Button btnPay;

    int stdID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //show progress dialog to make user wait for something.
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("الرجاء الانتظار");
        progressDialog.show();

        cvPaymentStatus = (CardView) findViewById(R.id.cvPaymentStatus);
        tvPaid = (TextView) findViewById(R.id.tvPaid);
        tvUnPaid = (TextView) findViewById(R.id.tvUnPaid);
        btnPay = (Button) findViewById(R.id.btnPay);

        btnPay.setOnClickListener(this);

        stdID = SharedPrefManager.getInstance(this).getStudent().getId();

        Call<CheckPaymentResponse> call = ApiClient.getInstance().getApi().paymentCheck(stdID);

        call.enqueue(new Callback<CheckPaymentResponse>() {
            @Override
            public void onResponse(Call<CheckPaymentResponse> call, Response<CheckPaymentResponse> response) {
                progressDialog.dismiss();
                cvPaymentStatus.setVisibility(View.VISIBLE);

                if (!response.body().isError()){
                    tvUnPaid.setVisibility(View.VISIBLE);
                    btnPay.setVisibility(View.VISIBLE);
                }else{
                    tvPaid.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CheckPaymentResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void payment(){

        stdID = SharedPrefManager.getInstance(this).getStudent().getId();

        Call<DefaultResponse> call = ApiClient.getInstance().getApi().payment(stdID);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (!response.body().isError()){
                    Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPay:
                payment();
                break;
        }
    }
}
