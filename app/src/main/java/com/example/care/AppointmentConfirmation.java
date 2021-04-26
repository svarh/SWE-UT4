package com.example.care;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AppointmentConfirmation extends AppCompatActivity {
    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";

    CollectionReference businessesCollection = rootRef.collection("Businesses");
    private GuestAccount guest;
    private UserModel userModel;

    private String organization;
    private String date;
    private String time;
    private String confirmationCode ;
    private String officer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_confirmation);

        userModel = new UserModel(this);
        guest = getIntent().getExtras().getParcelable("User");

        userModel.hideStatusBar();

        if (getIntent().hasExtra("date")){
            TextView confirmationDateTv = (TextView) findViewById(R.id.confirmationDateTv);
            String text = getIntent().getExtras().getString("date");
            confirmationDateTv.setText(text);
        }
        if (getIntent().hasExtra("time")){
            TextView confirmationTimeTv = (TextView) findViewById(R.id.confirmationTimeTv);
            String text = getIntent().getExtras().getString("time");
            confirmationTimeTv.setText(text + ":00");
        }
        if (getIntent().hasExtra("confirmationCode")){
            TextView confirmCodeTv = (TextView) findViewById(R.id.confirmCodeTv);
            String text = getIntent().getExtras().getString("confirmationCode");
            confirmCodeTv.setText(text);
        }

        //send appointment data {organization: ? , date: ?, time: ?, confirmationCode: ?} to Google fireStore as Hashmap
        //??
        organization = getIntent().getExtras().getString("organization");
        officer = getIntent().getExtras().getString("officer");
        date = getIntent().getExtras().getString("date");
        time = getIntent().getExtras().getString("time");
        confirmationCode = getIntent().getExtras().getString("confirmationCode");

        guest.makeAppointment(organization, officer, guest.getName(), date, time, confirmationCode);

        updateGuest();

       Button guestHomeBtn = (Button) findViewById(R.id.guestHomeBtn);

        guestHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentConfirmation.this, GuestHome.class);
                intent.putExtra("User", guest);
                startActivity(intent);
            }
        });
    }

    public void updateGuest(){
        rootRef.collection("Users").document(guest.getEmail())
                .set(guest)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void getBusiness(){

    }

}