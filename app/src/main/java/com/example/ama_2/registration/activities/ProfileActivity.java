package com.example.ama_2.registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.ama_2.registration.model.Student;
import com.example.ama_2.registration.R;
import com.example.ama_2.registration.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvStdID, tvPhone;

    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Student student = SharedPrefManager.getInstance(this).getStudent();

        tvName = (TextView) findViewById(R.id.tvProfileName);
        tvEmail = (TextView) findViewById(R.id.tvProfileEmail);
        tvPhone = (TextView) findViewById(R.id.tvProfilePhone);
        tvStdID = (TextView) findViewById(R.id.tvProfileStdID);

        tvName.append(student.getName());
        tvStdID.append(student.getStdID());
        tvPhone.append(student.getPhone());
        tvEmail.append(student.getEmail());

    }
}