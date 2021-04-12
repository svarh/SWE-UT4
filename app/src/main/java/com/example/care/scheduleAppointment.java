package com.example.care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class scheduleAppointment extends AppCompatActivity {
    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    CollectionReference subjectsRef = rootRef.collection("Businesses");
    private static final String TAG = "scheduleAppointment";

    private TextView theDate;
    private Button btnGoCalendar;

    private GuestAccount guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        //create organization spinner from array
        Spinner orgSpinner = (Spinner) findViewById(R.id.orgSpinner);
        List<String> organizations = new ArrayList<>();//holds company names

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),   android.R.layout.simple_spinner_item, organizations);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orgSpinner.setAdapter(spinnerArrayAdapter);
        subjectsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String organization = document.getString("companyName");
                        organizations.add(organization);
                    }
                    spinnerArrayAdapter.notifyDataSetChanged();
                }
            }
        });

        //direct user to calender
        theDate = (TextView) findViewById(R.id.date);
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);
        //display selected date from calender
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
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
                startIntent.putExtra("User", guest);
                startActivity(startIntent);
            }
        });
    }

}