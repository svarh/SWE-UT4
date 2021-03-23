package com.example.care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class scheduleAppointment extends AppCompatActivity {

    private static final String TAG = "scheduleAppointment";

    private TextView theDate;
    private Button btnGoCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        //create organization spinner from array
        String colors[] = {"Organization 1","Organization 2","Organization 3"};
        Spinner orgSpinner = (Spinner) findViewById(R.id.orgSpinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        orgSpinner.setAdapter(spinnerArrayAdapter);

        //direct user to calender
        theDate = (TextView) findViewById(R.id.date);
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scheduleAppointment.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        //show time slots when getTimeBtn is clicked
        Button getTimeBtn = (Button) findViewById(R.id.getTimeBtn);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);

        getTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.VISIBLE);
                button5.setVisibility(View.VISIBLE);
                button6.setVisibility(View.VISIBLE);
                button7.setVisibility(View.VISIBLE);
                button8.setVisibility(View.VISIBLE);
                button9.setVisibility(View.VISIBLE);
            }
        });
        //direct user to confirmation page

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonTime = b.getText().toString();
                Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                //show how to pass info to another activity
                startIntent.putExtra("com.example.care.Date", date);
                startIntent.putExtra("com.example.care.Time", buttonTime);
                startActivity(startIntent);
            }
        });
    }

}