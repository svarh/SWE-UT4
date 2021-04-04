package com.example.care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BusinessAppointments extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";
    private static Activity thisActivity = null;

    private RecyclerView rvList;
    private Button accountHome;

    private ArrayList<String> appointments = new ArrayList<>();
    private UserModel userModel;
    private BusinessAccount businessAccount;
    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_appointments);

        this.userModel = new UserModel(this);
        businessAccount = getIntent().getExtras().getParcelable("BusinessAcc");
        business = getIntent().getExtras().getParcelable("Business");

        appointments.add("Officer 1: 03/14/21, 09:00am, Guest1");
        appointments.add("Officer 3: 03/14/21, 03:00pm, Guest2");
        appointments.add("Officer 6: 03/15/21, 11:00am, Guest3");
        appointments.add("Officer 2: 03/17/21, 12:00pm, Guest4");
        appointments.add("Officer 5: 03/17/21, 12:00pm, Guest5");

        setUI();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAccountHome:
                Intent intent = new Intent(BusinessAppointments.this, BusinessHome.class);
                intent.putExtra("BusinessAcc", businessAccount);
                intent.putExtra("Business", business);
                startActivity(intent);
                break;
        }
    }

    public void setUI(){
        rvList = findViewById(R.id.rvScheduledAppt);
        accountHome = findViewById(R.id.btnAccountHome);
        accountHome.setOnClickListener(this);

        RVGuestResultsAdapter adapter = new RVGuestResultsAdapter(appointments, this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }
}