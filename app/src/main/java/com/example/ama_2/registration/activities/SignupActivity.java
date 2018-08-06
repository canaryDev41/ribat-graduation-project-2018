package com.example.ama_2.registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.R;
import com.example.ama_2.registration.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etName;
    EditText etEmail;
    EditText etPhone;
    EditText etStdID;
    EditText etPassword;

    Button btnSignup;

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etStdID = (EditText) findViewById(R.id.etStdID);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnSignup = (Button) findViewById(R.id.btnRegister);
        btnSignup.setOnClickListener(this);

    }

    private void studentSignup(){
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String stdID = etStdID.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        /*
          here i can implement more validation technique
         */

        if (name.isEmpty()){
            etName.setError("name is required");
            etName.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("enter valid email");
        }

        if (email.isEmpty()){
            etEmail.setError("email is required");
            etEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            etPhone.setError("phone is required");
            etPhone.requestFocus();
            return;
        }

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

        Call<DefaultResponse> call = ApiClient.getInstance().getApi().register(name, email, phone, stdID, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if (response.code() == 200){

                    DefaultResponse msg = response.body();

                    Toast.makeText(SignupActivity.this, msg.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    DefaultResponse msg = response.body();

                    Toast.makeText(SignupActivity.this, msg.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                studentSignup();
                break;
        }
    }
}
