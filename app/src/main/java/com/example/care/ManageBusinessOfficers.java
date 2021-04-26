package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ManageBusinessOfficers extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";
    private static Activity thisActivity = null;

    private RecyclerView recyclerView;
    private EditText firstNameText;
    private EditText lastNameText;
    private Button addOfficer;
    private Button removeOfficer;
    private Button accountHome;

    private Business business;
    private BusinessAccount businessAccount;
    private UserModel userModel;
    private Officer officer;
    private ArrayList<String> officerNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_business_officers);

        this.userModel = new UserModel(this);
        userModel.hideStatusBar();

        business = getIntent().getExtras().getParcelable("Business");
        businessAccount = getIntent().getExtras().getParcelable("BusinessAcc");

        setUI();

    }

    @Override
    public void onClick(View view)
    {
        String firstName;
        String lastName;
        switch (view.getId()) {
            case R.id.btnAddOfficer:
                firstName = firstNameText.getText().toString().trim();
                lastName = lastNameText.getText().toString().trim();
                if(firstName.length() == 0 | lastName.length() == 0){
                    userModel.makeToast("Please provide a first and last name");
                }
                else {
                    if(!business.addOfficer(firstName, lastName)){
                        userModel.makeToast("Officer already exists!");
                    }
                    else {
                        saveData();
                        officer = new Officer(firstName, lastName, business.getCompanyName());
                        saveOfficer();
                        Intent intent = new Intent(ManageBusinessOfficers.this, ManageBusinessOfficers.class);
                        intent.putExtra("BusinessAcc", businessAccount);
                        intent.putExtra("Business", business);
                        startActivity(intent);
                    }
                }
                break;

            case R.id.btnRemoveOfficer:
                firstName = firstNameText.getText().toString().trim();
                lastName = lastNameText.getText().toString().trim();
                if(firstName.length() == 0 | lastName.length() == 0){
                    userModel.makeToast("Please provide a first and last name");
                }
                else {
                    business.removeOfficer(firstName, lastName);
                    saveData();
                    officer = new Officer(firstName, lastName, business.getCompanyName());
                    removeOfficer();
                    Intent intent = new Intent(ManageBusinessOfficers.this, ManageBusinessOfficers.class);
                    intent.putExtra("BusinessAcc", businessAccount);
                    intent.putExtra("Business", business);
                    startActivity(intent);
                }
                break;

            case R.id.btnAccountHome:
                Intent intent = new Intent(ManageBusinessOfficers.this, BusinessHome.class);
                intent.putExtra("BusinessAcc", businessAccount);
                intent.putExtra("Business", business);
                startActivity(intent);
                break;
        }
    }

    public void setUI(){

        recyclerView = findViewById(R.id.rvOfficersList);
        firstNameText = findViewById(R.id.etFirst);
        lastNameText = findViewById(R.id.etLast);
        addOfficer = findViewById(R.id.btnAddOfficer);
        addOfficer.setOnClickListener(this);
        removeOfficer = findViewById(R.id.btnRemoveOfficer);
        removeOfficer.setOnClickListener(this);
        accountHome = findViewById(R.id.btnAccountHome);
        accountHome.setOnClickListener(this);

        RVOfficerAdapter adapter = new RVOfficerAdapter(business.getOfficers(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void saveData(){
        db.collection("Businesses").document(business.getCompanyName())
                .set(business)
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

    private void saveOfficer() {
        db.collection("Businesses").document(business.getCompanyName()).collection("OfficersList").document(officer.getName())
                .set(officer)
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

    private void removeOfficer() {
        db.collection("Businesses").document(business.getCompanyName()).collection("OfficersList").document(officer.getName())
                .delete()
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
}