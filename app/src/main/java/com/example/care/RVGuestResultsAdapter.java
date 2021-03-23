package com.example.care;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVGuestResultsAdapter extends RecyclerView.Adapter<RVGuestResultsAdapter.ViewHolder>{
    private static final String TAG = "RVResultsAdapter";

    private ArrayList<String> results = new ArrayList<>();
    private Context context;

    public RVGuestResultsAdapter (ArrayList<String> results, Context context){
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_covid_results, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:called.");
        holder.result.setText(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView result;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.tvResult);
        }
    }
}
