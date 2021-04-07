package com.example.care;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.rpc.Code;

import java.util.ArrayList;
import java.util.Random;

public class RecViewConfirmation extends RecyclerView.Adapter<RecViewConfirmation.MyViewHolder>{
    private ArrayList<Confirmation> confirmationArrayList;

    public RecViewConfirmation(ArrayList<Confirmation> confirmationArrayList){
        this.confirmationArrayList = confirmationArrayList;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView codeText;

        public MyViewHolder(final View view){
            super(view);
            codeText = view.findViewById(R.id.textCode);

        }
    }

    @NonNull
    @Override
    public RecViewConfirmation.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_codes, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewConfirmation.MyViewHolder holder, int position) {
        holder.codeText.setText(confirmationArrayList.get(position).getTextCode);

    }

    @Override
    public int getItemCount() {
        return confirmationArrayList.size();
    }


}
