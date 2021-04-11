package com.example.care;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckInGuest extends AppCompatActivity implements View.OnClickListener{

    private static Activity thisActivity = null;

    // UI elements
    private EditText input;
    private CheckBox checkBoxCode;
    private CheckBox checkBoxEmail;
    private Button buttonSearch;
    private LinearLayout layoutAppointment;
    private TextView txtName;
    private TextView txtEmail;
    private TextView txtCode;
    private Button buttonCheckIn;

    private BusinessAccount businessAccount;
    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_guest);

        businessAccount = getIntent().getExtras().getParcelable("BusinessAcc");
        business = getIntent().getExtras().getParcelable("Business");

        this.thisActivity = this;

        initialize();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonSearch:
                if (this.checkBoxCode.isChecked()) {
                    searchAppointmentByCode(this.input.getText().toString());
                } else if (this.checkBoxEmail.isChecked()) {
                    searchAppointmentByEmail(this.input.getText().toString());
                }
                break;

            case R.id.buttonCheckIn:
                checkIn();
                break;
        }
    }

    // This method initialize properties and UI
    private void initialize(){

        // Initialize UI elements
        this.input = findViewById(R.id.editTextSearchInput);
        this.checkBoxCode = findViewById(R.id.checkBoxByCode);
        this.checkBoxEmail = findViewById(R.id.checkBoxByEmail);
        this.buttonSearch = findViewById(R.id.buttonSearch);
        this.buttonSearch.setOnClickListener(this);

        this.layoutAppointment = findViewById(R.id.layoutAppointment);
        this.txtName = findViewById(R.id.txtGuestName);
        this.txtEmail = findViewById(R.id.txtGuestEmail);
        this.txtCode = findViewById(R.id.txtGuestConfirmationCode);
        this.buttonCheckIn = findViewById(R.id.buttonCheckIn);
        this.buttonCheckIn.setOnClickListener(this);

        // Make sure Appointment layout is hidden
        this.layoutAppointment.setVisibility(View.INVISIBLE);

        // Initialize Check Boxes
        this.checkBoxCode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(this.checkBoxCode.isChecked()){
                this.checkBoxEmail.setChecked(false);
            }
            this.input.setHint("Confirmation Code");
        });

        this.checkBoxEmail.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(this.checkBoxEmail.isChecked()){
                this.checkBoxCode.setChecked(false);
            }
            this.input.setHint("Email");
        });
    }

    private void checkIn() {
        // TODO: Mark this appointment as visited
    }

    private void searchAppointmentByCode (String code) {
        // TODO: Get appointment by code from database

        // Paste Guest name here
        String guestName = "";

        // Paste Guest email here
        String guestEmail = "";

        showAppointmentDetail(guestName, guestEmail, code);
    }

    private void searchAppointmentByEmail (String email) {
        // TODO: Get appointment by email from database

        // Paste Guest name here
        String guestName = "";

        // Paste Confirmation Code here
        String code = "";

        showAppointmentDetail(guestName, email, code);
    }

    private void showAppointmentDetail (String guestName, String guestEmail, String code) {
        // View Appointment layout
        this.layoutAppointment.setVisibility(View.VISIBLE);

        // Set up TextView
        this.txtName.setText("Name: " + guestName);
        this.txtEmail.setText("Email: " + guestEmail);
        this.txtCode.setText("Confirmation Code: " + code);
    }
}
