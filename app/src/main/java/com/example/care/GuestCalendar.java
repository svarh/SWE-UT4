package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class GuestCalendar extends AppCompatActivity implements View.OnClickListener{

    static Activity thisActivity = null;

    private CalendarView calendarView;
    private Button accountHome;
    private TextView organization;
    private TextView officer;
    private TextView time;
    private TextView confirmation;

    private GuestAccount guest;
    private UserModel userModel;

    private ArrayList<HashMap<String, String>> appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_calendar);

        guest = getIntent().getExtras().getParcelable("User");

        userModel = new UserModel(this);
        userModel.hideStatusBar();

        setUI();

        appointments = guest.getAppointments();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                organization.setText("Organization: \n");
                officer.setText("Officer: \n");
                time.setText("Time: ");
                confirmation.setText("Confirmation: \n");

                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                for(int i = 0; i < appointments.size(); i++){
                    HashMap<String, String> appointment = appointments.get(i);
                    if(appointment.get("Date").equals(date)){
                        organization.setText("Organization: \n" + appointment.get("Organization"));
                        officer.setText("Officer: \n" + appointment.get("Officer"));
                        time.setText("Time: " + appointment.get("Time"));
                        confirmation.setText("Confirmation: \n" + appointment.get("Confirmation"));
                    }
                }
            }
        });

    }

    public void setUI(){
        calendarView = findViewById(R.id.guestCalendar);
        accountHome = findViewById(R.id.btnAccountHome);
        accountHome.setOnClickListener(this);
        organization = findViewById(R.id.tvOrg);
        officer = findViewById(R.id.tvOfficer);
        time = findViewById(R.id.tvTime);
        confirmation = findViewById(R.id.tvConfirmation);
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