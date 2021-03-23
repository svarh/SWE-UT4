package com.example.care;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BusinessHome extends Activity implements View.OnClickListener {

    static Activity thisActivity = null;

    private Button btnAppointments = null;
    private Button btnManageOfficers = null;
    private Button btnCalendar = null;

   private BusinessAccount businessAccount;
   private Business business;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnAppointments:
                viewAppointmentsActivity(); //Survey Activity
                break;
            case R.id.btnManageOfficers:
                manageOfficersActivity(); //Upload Activity
                break;
            case R.id.btnCalendar:
                calendarActivity(); //Schedule Appointments Activity
                break;

        }
    }
    // This method initializes all the needed things
    private void initialize () {

        // Initialize Activity
        thisActivity = this;

        // Initialize buttons
        this.btnAppointments = findViewById(R.id.btnAppointments);
        this.btnAppointments.setOnClickListener(this);
        this.btnManageOfficers = findViewById(R.id.btnManageOfficers);
        this.btnManageOfficers.setOnClickListener(this);
        this.btnCalendar = findViewById(R.id.btnCalendar);
        this.btnCalendar.setOnClickListener(this);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        businessAccount = getIntent().getExtras().getParcelable("BusinessAcc");
        business = getIntent().getExtras().getParcelable("Business");

        initialize();

    }
    // This method creates Toast to send notification
    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }
    private void viewAppointmentsActivity(){
        Intent intent = new Intent(this, BusinessAppointments.class);
        intent.putExtra("BusinessAcc", businessAccount);
        intent.putExtra("Business", business);
        startActivity(intent);
    }
    private void manageOfficersActivity(){
        Intent intent = new Intent(this, ManageBusinessOfficers.class);
        intent.putExtra("BusinessAcc", businessAccount);
        intent.putExtra("Business", business);
        startActivity(intent);
    }
    private void calendarActivity(){
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra("BusinessAcc", businessAccount);
        intent.putExtra("Business", business);
        startActivity(intent);
    }

}
