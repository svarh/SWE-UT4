package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class BusinessCalendar extends AppCompatActivity implements View.OnClickListener {

    static Activity thisActivity = null;

    private CalendarView calendarView;
    private Button accountHome;

    private BusinessAccount businessAccount;
    private Business business;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_calendar);

        this.userModel = new UserModel(this);
        userModel.hideStatusBar();

        business = getIntent().getExtras().getParcelable("Business");
        businessAccount = getIntent().getExtras().getParcelable("BusinessAcc");

        setUI();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAccountHome:
                Intent intent = new Intent(BusinessCalendar.this, BusinessHome.class);
                intent.putExtra("BusinessAcc", businessAccount);
                intent.putExtra("Business", business);
                startActivity(intent);
                break;
        }
    }

    public void setUI(){
        calendarView = findViewById(R.id.businessCalendar);
        accountHome = findViewById(R.id.btnAccountHome);
        accountHome.setOnClickListener(this);
    }
}