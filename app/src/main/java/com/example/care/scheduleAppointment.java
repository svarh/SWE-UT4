package com.example.care;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class scheduleAppointment extends AppCompatActivity {
    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    CollectionReference businessesCollection = rootRef.collection("Businesses");
    private static final String TAG = "scheduleAppointment";

    private TextView theDate;
    private Button btnGoCalendar;

    private GuestAccount guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        //create organization spinner/drop down menu from organizations array
        Spinner orgSpinner = (Spinner) findViewById(R.id.orgSpinner);
        List<String> organizations = new ArrayList<>();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),   android.R.layout.simple_spinner_item, organizations);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orgSpinner.setAdapter(spinnerArrayAdapter);
        businessesCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //create officer spinner/drop down menu from officers array
        Spinner officerSpinner = (Spinner) findViewById(R.id.officerSpinner);
        List<String> officers = new ArrayList<>();

        ArrayAdapter<String> officerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),   android.R.layout.simple_spinner_item, officers);
        officerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        officerSpinner.setAdapter(officerArrayAdapter);
        businessesCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        List<String> officerArray = (List<String>) document.get("officers");
                        for (String item : officerArray) {
                            officers.add(item);
                        }
                    }
                    officerArrayAdapter.notifyDataSetChanged();
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

//        //retrieve time slots when getTimeBtn is clicked
        Button getTimeBtn = (Button) findViewById(R.id.getTimeBtn);
        getTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String organization = orgSpinner.getSelectedItem().toString();
                String officer = officerSpinner.getSelectedItem().toString();

                businessesCollection.document(organization).collection("OfficersList").document(officer).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists() && date != null) {
                                ArrayList<String> timeSlots = new ArrayList<String>();
                                timeSlots.clear();
                                Map<String, Object> officerMap = document.getData();//returns a Map object
                                Map<String, Object> appointmentsMap = (Map<String, Object>) officerMap.get("appointments");
                                List<Object> availableTimes = (List<Object>) appointmentsMap.get("0"+date);
                                if (availableTimes != null){
                                Log.d(TAG, "DocumentSnapshot name: " + officerMap.get("name"));
                                Log.d(TAG, "DocumentSnapshot scheduleData: " + availableTimes.get(0));
                                Log.d(TAG, "DocumentSnapshot scheduleData: " + availableTimes.size());

                                for(int i = 0; i < availableTimes.size(); i++)
                                {
                                    Map<String, Object> time = (Map<String, Object>) availableTimes.get(i);
                                    Log.d(TAG, "time: " + time.get("time"));
                                    timeSlots.add(time.get("time").toString());
                                }


                                Log.d(TAG, "time slots: " + timeSlots);
                                addBtnInXML(timeSlots, organization, officer, date);

                            } else {
                                Log.d(TAG, "No such document");
                            } }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        });

    }
    //dynamically render buttons in XML based on ArrayList
    public void addBtnInXML(ArrayList<String> timeSlots, String organization, String officer, String date){
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 0, 0, 30);

        for(int i = 0; i < timeSlots.size(); i++) {
            Button newButton = new Button(this);
            newButton.setText(timeSlots.get(i));
            newButton.setBackgroundColor(0xFF99D6D6);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String confirmationCode = getConfirmationCode();
                    Log.d(TAG, "Code: " + confirmationCode);
                    Button b = (Button)view;
                    String buttonTime = b.getText().toString();
                    Intent startIntent = new Intent(getApplicationContext(), AppointmentConfirmation.class);
                    startIntent.putExtra("organization", organization);
                    startIntent.putExtra("officer", officer);
                    startIntent.putExtra("date", date);
                    startIntent.putExtra("time", buttonTime);
                    startIntent.putExtra("confirmationCode", confirmationCode);
                    startIntent.putExtra("User", guest);
                    startActivity(startIntent);
                }
            });;
            layout.addView(newButton, layoutParams);
        }

    }
    //generate confirmation code of 5 chars
    public String getConfirmationCode(){
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        String confirmationCode = salt.toString();
        return confirmationCode;
    }

}