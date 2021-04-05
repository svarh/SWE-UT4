package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Login extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";
    private static Activity thisActivity = null;

    private EditText emailInput;
    private EditText passwordInput;
    private CheckBox guestCheck;
    private CheckBox businessCheck;
    private Button logInButton;

    private String email;
    private String password;
    private boolean isGuest;

    private UserModel userModel;
    //private Business business = new Business("");

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonLogIn:
                hideKeyboard(v);
                this.email = emailInput.getText().toString().trim().toLowerCase();
                this.password = passwordInput.getText().toString().trim();
                this.userModel.login(this.email, this.password);
                if(userModel.login(email, password)){
                    getData();
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.thisActivity = this;

        initialize();
    }

    // This method initialize properties and UI
    private void initialize(){

        // Initialize properties
        this.email = "";
        this.password = "";
        this.isGuest = true;
        this.userModel = new UserModel(this.thisActivity);

        // Initialize UI elements
        this.emailInput = findViewById(R.id.editTextEmail);
        this.passwordInput = findViewById(R.id.editTextPassword);
        this.guestCheck = findViewById(R.id.checkBoxGuest);
        this.businessCheck = findViewById(R.id.checkBoxBusiness);
        this.logInButton = findViewById(R.id.buttonLogIn);
        this.logInButton.setOnClickListener(this);

        // Initialize Check Boxes
        this.guestCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(this.guestCheck.isChecked()){
                this.businessCheck.setChecked(false);
            }
            this.emailInput.setHint("Email");
        });

        this.businessCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(this.businessCheck.isChecked()){
                this.guestCheck.setChecked(false);
            }
            this.emailInput.setHint("Company Email");

        });
    }

    public boolean checkInputs(){
        if (email.length() == 0 | password.length() == 0){
            userModel.makeToast("Please fill in all available fields");
            return false;
        }
        return true;
    }

    private void getData(){
        DocumentReference docRef = db.collection("Users").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Document exists!");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        if(guestCheck.isChecked()) {
                            if((Boolean) document.getData().get("guest")) {
                                GuestAccount g = new GuestAccount((String) document.getData().get("email"), (String) document.getData().get("password"), (String) document.getData().get("name"), (String) document.getData().get("phone"));
                                if (g.getPassword().equals(password)) {
                                    userModel.makeToast("Login Successful!");

                                    Intent intent = new Intent(Login.this, GuestHome.class);
                                    intent.putExtra("User", g);
                                    startActivity(intent);
                                } else {
                                    userModel.makeToast("Incorrect password. Please try again!");
                                }
                            }
                            else{
                                userModel.makeToast("There is no guest account under this email");
                            }
                        }
                        else if(businessCheck.isChecked()){
                            if(!(Boolean) document.getData().get("guest")) {
                                BusinessAccount businessAccount = new BusinessAccount((String) document.getData().get("companyEmail"), (String) document.getData().get("password"), (String) document.getData().get("companyName"), (String) document.getData().get("phone"));
                                if (businessAccount.getPassword().equals(password)) {
                                    userModel.makeToast("Login Successful!");
                                    getBusiness(businessAccount);
                                } else {
                                    userModel.makeToast("Incorrect password. Please try again!");
                                }
                            }
                            else{
                                userModel.makeToast("There is no business account under this email");
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Incorrect email. Please try again!",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Document does not exist!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Error! Please Try Again.",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });
    }

    public void getBusiness(BusinessAccount businessAccount){
        Business business = new Business("");
        DocumentReference docRef = db.collection("Businesses").document(businessAccount.getCompanyName());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Document exists!");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        business.setCompanyName((String) document.getData().get("companyName"));
                        business.setOfficers((ArrayList<String>) document.get("officers"));
                        Log.d(TAG, "Officers in class" + business.getOfficers());

                        Intent intent = new Intent(Login.this, BusinessHome.class);
                        intent.putExtra("BusinessAcc", businessAccount);
                        intent.putExtra("Business", business);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),"ERROR! Please try again!",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Document does not exist!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Error! Please Try Again.",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}