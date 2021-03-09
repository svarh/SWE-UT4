package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AccountHome extends AppCompatActivity {

    private Button  accountSettings;
    private Button  manageAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);
    }

    private void setUI(){
        accountSettings = findViewById(R.id.btnAccSettings);
        manageAppointments = findViewById(R.id.btnManage);
    }
}