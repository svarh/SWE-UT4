package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class GuestCalendar extends AppCompatActivity implements View.OnClickListener{

    static Activity thisActivity = null;

    private CalendarView calendarView;
    private Button accountHome;

    private GuestAccount guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_calendar);

        guest = getIntent().getExtras().getParcelable("User");

        setUI();

    }

    public void setUI(){
        calendarView = findViewById(R.id.guestCalendar);
        accountHome = findViewById(R.id.btnAccountHome);
        accountHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAccountHome:
                Intent intent = new Intent(GuestCalendar.this, GuestHome.class);
                intent.putExtra("User", guest);
                startActivity(intent);
                break;
        }
    }
}