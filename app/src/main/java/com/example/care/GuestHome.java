package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class GuestHome extends AppCompatActivity implements View.OnClickListener {

    static Activity thisActivity = null;

    private Button btnTakeSurvey = null;
    private Button btnCovidResults = null;
    private Button btnScheduleAppts = null;
    private Button btnCalendar = null;
    private Button btnExploreBusiness = null;
    private Button btnBusinessVisited = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnTakeSurvey:
                surveyActivity(); //Survey Activity
                break;
            case R.id.btnCovidResults:
                uploadResultsActivity(); //Upload Activity
                break;
            case R.id.btnScheduleAppts:
                scheduleApptActivity(); //Schedule Appointments Activity
                break;
            case R.id.btnCalendar:
                calendarActivity(); //Calendar Activity
                break;
            case R.id.btnExploreBusiness:
                exploreBusinessActivity();
                break;
            case R.id.btnBusinessVisited:
                visitedBusinessActivity();
                break;


        }
    }

    // This method initializes all the needed things
    private void initialize () {

        // Initialize Activity
        thisActivity = this;

        // Initialize buttons
        this.btnTakeSurvey = findViewById(R.id.btnTakeSurvey);
        this.btnTakeSurvey.setOnClickListener(this);
        this.btnCovidResults = findViewById(R.id.btnCovidResults);
        this.btnCovidResults.setOnClickListener(this);
        this.btnScheduleAppts = findViewById(R.id.btnScheduleAppts);
        this.btnScheduleAppts.setOnClickListener(this);
        this.btnCalendar = findViewById(R.id.btnCalendar);
        this.btnCalendar.setOnClickListener(this);
        this.btnExploreBusiness = findViewById(R.id.btnExploreBusiness);
        this.btnExploreBusiness.setOnClickListener(this);
        this.btnBusinessVisited = findViewById(R.id.btnBusinessVisited);
        this.btnBusinessVisited.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);

        initialize();

    }


    // This method creates Toast to send notification
    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }

    private void surveyActivity(){
        Intent i = new Intent(this, Survey.class);
        startActivity(i);
    }

    private void uploadResultsActivity(){
        Intent intent = new Intent(this, UploadResults.class);
        startActivity(intent);
    }
    private void scheduleApptActivity(){
        Intent intent = new Intent(this, GuestAppointments.class);
        startActivity(intent);
    }
    private void calendarActivity(){
        Intent intent = new Intent(this, GuestCalendar.class);
        startActivity(intent);
    }

    private void exploreBusinessActivity(){
        Intent intent = new Intent(this, GuestExploreBusiness.class);
        startActivity(intent);
    }
    private void visitedBusinessActivity(){
        Intent intent = new Intent(this, GuestVisitedBusiness.class);
        startActivity(intent);
    }

}

