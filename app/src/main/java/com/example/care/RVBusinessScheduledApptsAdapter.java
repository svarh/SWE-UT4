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

public class RVBusinessScheduledApptsAdapter extends RecyclerView.Adapter<RVBusinessScheduledApptsAdapter.ViewHolder>{
    private static final String TAG = "RVEBAdapter";

    private ArrayList<String> appointments = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_business_scheduled_appointments, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:called.");
        holder.appointment.setText(appointments.get(position));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView appointment;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appointment = itemView.findViewById(R.id.tvAppointment);
            parentLayout = itemView.findViewById(R.id.visited_parentLayout);
        }
    }
}
