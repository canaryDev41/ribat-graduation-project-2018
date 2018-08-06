package com.example.ama_2.registration.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ama_2.registration.R;
import com.example.ama_2.registration.model.Excuse;
import java.util.ArrayList;

public class ExcusesAdapter extends RecyclerView.Adapter<ExcusesAdapter.ExcusesViewHolder>{

    private Context mCtx;
    private ArrayList<Excuse> excuseArrayList;

    public ExcusesAdapter(Context mCtx, ArrayList<Excuse> excuseArrayList) {
        this.mCtx = mCtx;
        this.excuseArrayList = excuseArrayList;
    }

    @Override
    public ExcusesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_excuse, parent, false);
        return new ExcusesAdapter.ExcusesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExcusesViewHolder holder, int position) {
        final Excuse excuse = excuseArrayList.get(position);

        holder.tvType.setText(excuse.getType());
        holder.tvFrom.setText(excuse.getFrom());
        holder.tvTo.setText(excuse.getTo());
        holder.tvStatus.setText(excuse.getStatus());
        //holder.tvAttach.setText(excuse.getAttach());
    }

    @Override
    public int getItemCount() {
        return excuseArrayList.size();
    }

    class ExcusesViewHolder extends RecyclerView.ViewHolder{

        TextView tvType, tvFrom, tvTo, tvStatus;
        ExcusesViewHolder(View view) {
            super(view);
            tvType = (TextView) view.findViewById(R.id.tvType);
            tvFrom = (TextView) view.findViewById(R.id.tvFrom);
            tvTo = (TextView) view.findViewById(R.id.tvTo);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        }
    }

}
