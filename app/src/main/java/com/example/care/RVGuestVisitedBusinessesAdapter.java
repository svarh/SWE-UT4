package com.example.care;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVGuestVisitedBusinessesAdapter extends RecyclerView.Adapter<RVGuestVisitedBusinessesAdapter.ViewHolder>{
    private static final String TAG = "EVVisitedAdapter";

    private ArrayList<String> businessNames = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_guest_visited_businesses, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:called.");
        holder.businessName.setText(businessNames.get(position));
    }

    @Override
    public int getItemCount() {
        return businessNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView businessName;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            businessName = itemView.findViewById(R.id.tvBusinessName);
            parentLayout = itemView.findViewById(R.id.visited_parentLayout);
        }
    }
}
