package com.example.ama_2.registration.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ama_2.registration.R;
import com.example.ama_2.registration.model.Form;

import java.util.ArrayList;

public class FormsAdapter extends RecyclerView.Adapter<FormsAdapter.FormsViewHolder>{

    private Context mCtx;
    private ArrayList<Form> formArrayList;

    public FormsAdapter(Context mCtx, ArrayList<Form> formArrayList) {
        this.mCtx = mCtx;
        this.formArrayList = formArrayList;
    }

    @Override
    public FormsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_form, parent, false);
        return new FormsAdapter.FormsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FormsViewHolder holder, int position) {
        final Form form = formArrayList.get(position);

        holder.tvType.setText(form.getType());
        holder.tvStatus.setText(form.getStatus());
        holder.tvDate.setText(form.getDate());
        //holder.tvAttach.setText(excuse.getAttach());
    }

    @Override
    public int getItemCount() {
        return formArrayList.size();
    }

    class FormsViewHolder extends RecyclerView.ViewHolder{

        TextView tvType, tvDate, tvStatus;
        FormsViewHolder(View view) {
            super(view);
            tvType = (TextView) view.findViewById(R.id.tvType);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
        }
    }

}
