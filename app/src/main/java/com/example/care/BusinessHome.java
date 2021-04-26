package com.example.care;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BusinessHome extends Activity implements View.OnClickListener {

    static Activity thisActivity = null;

    private LinearLayout btnAppointments = null;
    private LinearLayout btnCheckin = null;
    private LinearLayout btnManageOfficers = null;
    private LinearLayout btnCalendar = null;

    private Button logoutButton;

   private BusinessAccount businessAccount;
   private Business business;
   private UserModel userModel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnBusinessAppointment:
                viewAppointmentsActivity(); //Survey Activity
                break;
            case R.id.btnBusinessManageOfficer:
                manageOfficersActivity(); //Upload Activity
                break;
            case R.id.btnBusinessCalendar:
                calendarActivity(); //Schedule Appointments Activity
                break;
            case R.id.btnBusinessCheckIn:
                checkInActivity(); // Check in guest
                break;
            case R.id.btnLogout:
                Intent i = new Intent(this, Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
        }
    }
    // This method initializes all the needed things
    private void initialize () {

        // Initialize Activity
        thisActivity = this;

        // Initialize buttons
        this.btnAppointments = findViewById(R.id.btnBusinessAppointment);
        this.btnAppointments.setOnClickListener(this);
        this.btnManageOfficers = findViewById(R.id.btnBusinessManageOfficer);
        this.btnManageOfficers.setOnClickListener(this);
        this.btnCalendar = findViewById(R.id.btnBusinessCalendar);
        this.btnCalendar.setOnClickListener(this);
        this.btnCheckin = findViewById(R.id.btnBusinessCheckIn);
        this.btnCheckin.setOnClickListener(this);
        logoutButton = findViewById(R.id.btnLogout);
        logoutButton.setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        businessAccount = getIntent().getExtras().getParcelable("BusinessAcc");
        business = getIntent().getExtras().getParcelable("Business");

        userModel = new UserModel(this);
        userModel.hideStatusBar();

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
        Intent intent = new Intent(this, BusinessCalendar.class);
        intent.putExtra("BusinessAcc", businessAccount);
        intent.putExtra("Business", business);
        startActivity(intent);
    }

    private void checkInActivity(){
        Intent intent = new Intent(this, CheckInGuest.class);
        intent.putExtra("BusinessAcc", businessAccount);
        intent.putExtra("Business", business);
        startActivity(intent);
    }

}
