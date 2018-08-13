package com.example.ama_2.registration.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etSemester, etLevel, etBlood, etPhone, etParentName, etParentJob, etParentPhone, etNationality, etReligion;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etSemester = (EditText) findViewById(R.id.etSemester);
        etLevel = (EditText) findViewById(R.id.etLevel);

        etBlood = (EditText) findViewById(R.id.etBlood);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etParentName = (EditText) findViewById(R.id.etParentName);
        etParentJob = (EditText) findViewById(R.id.etParentJob);
        etParentPhone = (EditText) findViewById(R.id.etParentPhone);
        etNationality = (EditText) findViewById(R.id.etNationality);
        etReligion = (EditText) findViewById(R.id.etReligion);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

    }

    private void registration() {
        String semester = etSemester.getText().toString().trim();
        String level = etLevel.getText().toString().trim();

        String blood = etBlood.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String parentName = etParentName.getText().toString().trim();
        String parentJob = etParentJob.getText().toString().trim();
        String parentPhone = etParentPhone.getText().toString().trim();
        String nationality = etNationality.getText().toString().trim();
        String religion = etReligion.getText().toString().trim();

        int student_id = SharedPrefManager.getInstance(this).getStudent().getId();

        Call<DefaultResponse> call = ApiClient.getInstance().getApi().registration(student_id, semester, level, blood, phone, parentName, parentJob, parentPhone, nationality, religion);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (!response.body().isError()){
                    Toast.makeText(RegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnRegister:
                registration();
                break;
        }

    }

}
