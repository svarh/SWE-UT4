package com.example.care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_confirmation);

        if (getIntent().hasExtra("com.example.care.Date")){
            TextView confirmationDateTv = (TextView) findViewById(R.id.confirmationDateTv);
            String text = getIntent().getExtras().getString("com.example.care.Date");
            confirmationDateTv.setText(text);
        }
        if (getIntent().hasExtra("com.example.care.Time")){
            TextView confirmationTimeTv = (TextView) findViewById(R.id.confirmationTimeTv);
            String text = getIntent().getExtras().getString("com.example.care.Time");
            confirmationTimeTv.setText(text);
        }
       Button guestHomeBtn = (Button) findViewById(R.id.guestHomeBtn);

        guestHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentConfirmation.this, GuestHome.class);
                startActivity(intent);
            }
        });
    }
}