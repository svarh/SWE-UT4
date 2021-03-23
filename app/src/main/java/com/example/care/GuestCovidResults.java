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

public class GuestCovidResults extends AppCompatActivity implements View.OnClickListener{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";
    private static Activity thisActivity = null;

    private RecyclerView rvList;
    private Button accountHome;

    private ArrayList<String> results = new ArrayList<>();
    private UserModel userModel;
    private GuestAccount guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_covid_results);

        this.userModel = new UserModel(this);
        guest = getIntent().getExtras().getParcelable("User");

        results.add("January 15, 2021 - Passed");
        results.add("January 30, 2021 - Passed");
        results.add("February 20, 2021 - Failed");
        results.add("March 3, 2021 - Passed");
        results.add("March 20, 2021 - Failed");

        setUI();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAccountHome:
                Intent intent = new Intent(GuestCovidResults.this, GuestHome.class);
                intent.putExtra("User", guest);
                startActivity(intent);
                break;
        }
    }

    public void setUI(){
        rvList = findViewById(R.id.rvSurveyResults);
        accountHome = findViewById(R.id.btnAccountHome);
        accountHome.setOnClickListener(this);

        RVGuestResultsAdapter adapter = new RVGuestResultsAdapter(results, this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }
}