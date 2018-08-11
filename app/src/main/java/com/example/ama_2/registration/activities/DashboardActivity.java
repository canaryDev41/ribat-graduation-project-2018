package com.example.ama_2.registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.storage.SharedPrefManager;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    CardView card_result, card_register, card_logout, card_info, card_absent_excuses, card_forms, card_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        card_result = (CardView) findViewById(R.id.card_result);
        card_register = (CardView) findViewById(R.id.card_register);
        card_logout = (CardView) findViewById(R.id.card_logout);
        card_info = (CardView) findViewById(R.id.card_info);
        card_absent_excuses = (CardView) findViewById(R.id.card_absent_excuses);
        card_forms = (CardView) findViewById(R.id.card_forms);
        card_payment = (CardView) findViewById(R.id.card_payment);

        card_result.setOnClickListener(this);
        card_register.setOnClickListener(this);
        card_logout.setOnClickListener(this);
        card_info.setOnClickListener(this);
        card_absent_excuses.setOnClickListener(this);
        card_forms.setOnClickListener(this);
        card_payment.setOnClickListener(this);

    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        //enhance this by using switch statement!
        if (v == card_register) {
            startActivity(new Intent(this, RegDashboardActivity.class));
            //startActivity(new Intent(getApplicationContext(), Register.class));
        } else if (v == card_result) {
            startActivity(new Intent(this, ResultActivity.class));
        } else if (v == card_logout) {
            logout();
        } else if (v == card_info) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (v == card_absent_excuses) {
            startActivity(new Intent(this, ExcusesDashboardActivity.class));
        }else if (v == card_payment) {
            startActivity(new Intent(this, PaymentActivity.class));
        }else if (v == card_forms){
            startActivity(new Intent(this, FormActivity.class));
        }
    }

}
