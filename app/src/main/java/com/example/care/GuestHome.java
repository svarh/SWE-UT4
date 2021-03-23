package com.example.care;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GuestHome extends AppCompatActivity implements View.OnClickListener {

    static Activity thisActivity = null;

    private Button btnTakeSurvey = null;
    private Button btnCovidResults = null;
    private Button btnScheduleAppts = null;
    private Button btnCalendar = null;
    private Button btnExploreBusiness = null;
    private Button btnBusinessVisited = null;

    private GuestAccount guest;

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

        guest = getIntent().getExtras().getParcelable("User");

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
        Intent intent = new Intent(this, scheduleAppointment.class);
        startActivity(intent);
    }
    private void calendarActivity(){
        Intent intent = new Intent(this, GuestCalendar.class);
        startActivity(intent);
    }

    private void exploreBusinessActivity(){
        Intent intent = new Intent(this, GuestExploreBusiness.class);
        intent.putExtra("User", guest);
        startActivity(intent);
    }
    private void visitedBusinessActivity(){
        Intent intent = new Intent(this, GuestVisitedBusiness.class);
        startActivity(intent);
    }

}


