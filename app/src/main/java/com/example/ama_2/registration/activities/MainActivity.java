package com.example.ama_2.registration.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.LoginResponse;
import com.example.ama_2.registration.R;
import com.example.ama_2.registration.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etStdID, etPassword;

    Button btnLogin;
    TextView tvRegister;
    ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        etStdID = (EditText) findViewById(R.id.etStdID);
        etPassword = (EditText)findViewById(R.id.etPassword);

        tvRegister = (TextView)findViewById(R.id.tvRegister);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);

        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void studentLogin() {

        String stdID = etStdID.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (stdID.isEmpty()){
            etStdID.setError("stdID is required");
            etStdID.requestFocus();
            return;
        }

        if (password.isEmpty()){
            etPassword.setError("password is required");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            etPassword.setError("password must be larger than 6 characters");
            etPassword.requestFocus();
            return;
        }

        Call<LoginResponse> call = ApiClient
                                    .getInstance()
                                    .getApi()
                                    .studentLogin(stdID, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()){
                    //here student was saved...
                    SharedPrefManager.getInstance(getApplicationContext()).saveStudent(loginResponse.getStudent());

                    //here student redirected to dashboard activity
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                    studentLogin();
                break;

                case R.id.tvRegister:
                    startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;
        }
    }
}


