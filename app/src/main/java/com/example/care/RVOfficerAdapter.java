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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RVOfficerAdapter extends RecyclerView.Adapter<RVOfficerAdapter.ViewHolder>{

    private static final String TAG = "RVOfficerAdapter";

    private ArrayList<String> officerNames;
    private Context context;

    public RVOfficerAdapter(ArrayList<String> officerNames, Context context){
        this.officerNames = officerNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listofficers, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.officerName.setText(officerNames.get(position));

    }

    @Override
    public int getItemCount() {
        return officerNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout parentLayout;
        TextView officerName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.officer_parentLayout);
            officerName = itemView.findViewById(R.id.tvOfficerName);
        }
    }
}
