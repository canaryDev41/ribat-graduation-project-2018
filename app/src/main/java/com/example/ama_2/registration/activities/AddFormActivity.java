package com.example.ama_2.registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.DefaultResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFormActivity extends AppCompatActivity {

    Button btnConfirm;
    RadioGroup rgTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form);

        btnConfirm= (Button) findViewById(R.id.btnConfirm);
        rgTypes = (RadioGroup) findViewById(R.id.rgTypes);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stdID = SharedPrefManager.getInstance(AddFormActivity.this).getStudent().getId();

                int selectedId = rgTypes.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                String type = radioButton.getText().toString().trim();

                Call<DefaultResponse> call = ApiClient
                        .getInstance()
                        .getApi()
                        .studentForm(type, stdID);

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        Toast.makeText(AddFormActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddFormActivity.this, FormActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Toast.makeText(AddFormActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}
