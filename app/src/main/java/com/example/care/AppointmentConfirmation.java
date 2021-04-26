package com.example.care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AppointmentConfirmation extends AppCompatActivity {
    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    CollectionReference businessesCollection = rootRef.collection("Businesses");
    private GuestAccount guest;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_confirmation);

        guest = getIntent().getExtras().getParcelable("User");

        userModel = new UserModel(this);

        userModel.hideStatusBar();
        String organization = getIntent().getExtras().getString("organization");


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

        //send appointment data {organization: ? , data: ?, time: ?, confirmation: ?} to Google fireStore as Hashmap
        //??

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
}