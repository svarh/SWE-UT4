package com.example.care;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.rpc.Code;

import java.util.ArrayList;
import java.util.Random;

public class Confirmation extends AppCompatActivity{

    public String getTextCode = getRandomNumberString();
    private ArrayList<Confirmation> confirmationArrayList;
    private RecyclerView recyclerView;
    public Confirmation(String randomNumberString) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        recyclerView = findViewById(R.id.confirmationCode);
        confirmationArrayList = new ArrayList<>();
        
        setCodeInfo();
        setAdapter();
        
        getRandomNumberString();

    }

    private void setAdapter() {
        RecViewConfirmation adapter = new RecViewConfirmation(confirmationArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setCodeInfo() {
        confirmationArrayList.add(new Confirmation(getRandomNumberString()));
    }

    // Generate a random 6 digit code
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

}
