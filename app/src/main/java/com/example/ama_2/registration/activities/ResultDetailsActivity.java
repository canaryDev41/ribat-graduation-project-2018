package com.example.ama_2.registration.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.example.ama_2.registration.R;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.ResultDetails;
import com.example.ama_2.registration.model.ResultResponse;
import com.example.ama_2.registration.storage.SharedPrefManager;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDetailsActivity extends AppCompatActivity {

    private List<ResultDetails> resultDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري المعالجه");
        progressDialog.show();

        int studentID = SharedPrefManager.getInstance(this).getStudent().getId();

        ListView listView = (ListView)findViewById(R.id.listView);

        Call<ResultResponse> call = ApiClient
                .getInstance()
                .getApi()
                .studentResult(studentID);

        call.enqueue(new Callback<ResultResponse>() {

            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {


            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });


    }
}
