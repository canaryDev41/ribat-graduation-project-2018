package com.example.ama_2.registration.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.api.ApiClient;
import com.example.ama_2.registration.model.ResultDetails;
import com.example.ama_2.registration.model.ResultResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDetailsAdapter extends RecyclerView.Adapter<ResultDetailsAdapter.ResultViewHolder>{

    private Context mCtx;
    private ArrayList<ResultDetails> detailsList;

    public ResultDetailsAdapter(Context mCtx, ArrayList<ResultDetails> detailsList) {
        this.mCtx = mCtx;
        this.detailsList = detailsList;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_result_details, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultViewHolder holder, int position) {

        final ResultDetails resultDetails = detailsList.get(position);

        holder.tvCourseName.setText(resultDetails.getCourse_name());
        holder.tvCourseAppreciation.setText(resultDetails.getCourse_appreciation());
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder{

        TextView tvCourseName, tvCourseAppreciation;
        ResultViewHolder(View view) {
            super(view);
            tvCourseName = (TextView) view.findViewById(R.id.tvCourseName);
            tvCourseAppreciation = (TextView) view.findViewById(R.id.tvCourseAp);
        }
    }

}
