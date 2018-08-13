package com.example.ama_2.registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.R;
import com.example.ama_2.registration.model.ListWithKeys;
import com.example.ama_2.registration.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText etName;
    EditText etEmail;
    EditText etPhone;
    EditText etStdID;
    EditText etPassword;
    EditText etAcceptance_year;
    String departmentID;

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
        etAcceptance_year = (EditText) findViewById(R.id.etYear);

        btnSignup = (Button) findViewById(R.id.btnRegister);
        btnSignup.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        List<ListWithKeys> list = new ArrayList<ListWithKeys>();
        list.add(new ListWithKeys("information technology", 1));
        list.add(new ListWithKeys("computer science", 2));
        list.add(new ListWithKeys("computer network", 3));
        list.add(new ListWithKeys("information tech diploma", 4));

        // Creating adapter for spinner
        ArrayAdapter<ListWithKeys> dataAdapter = new ArrayAdapter<ListWithKeys>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    private void studentSignup(){
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String stdID = etStdID.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String acceptance_year = etAcceptance_year.getText().toString().trim();

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
            etEmail.requestFocus();
            return;
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

        Call<DefaultResponse> call = ApiClient.
                                        getInstance().
                                        getApi().
                                        register(name, email, phone, stdID, password, acceptance_year, departmentID);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ListWithKeys s = (ListWithKeys) adapterView.getItemAtPosition(i);
        Object key = s.key;

        departmentID = key.toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        departmentID = "1";
    }
}
