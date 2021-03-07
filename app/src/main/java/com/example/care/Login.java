package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    EditText emailInput;
    EditText passwordInput;
    CheckBox guestCheck;
    CheckBox businessCheck;
    Button signUpButton;

    String email;
    String password;
    String name;
    String phoneNumb;


    static Activity thisActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUI();
        thisActivity = this;

        guestCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(guestCheck.isChecked()){
                    businessCheck.setChecked(false);
                }
                emailInput.setHint("Email");
            }
        });

        businessCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(businessCheck.isChecked()){
                    guestCheck.setChecked(false);
                }
                emailInput.setHint("Company Email");

            }
        });

     /*   signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                if(!guestCheck.isChecked() & !businessCheck.isChecked()){
                    makeToast("Please select an account type");
                }
                else if(guestCheck.isChecked()){
                    if(checkInputs("Guest")){
                        if(checkValidity(password, email, phoneNumb)) {
                            makeToast("Account Created!");
                        }
                    }
                }
                else if(businessCheck.isChecked()){
                    if(checkInputs("Business")){
                        if(checkValidity(password, email, phoneNumb)) {
                            makeToast("Account Created!");
                        }
                    }
                }
            }
        });*/
    }

    // This method sets up the UI
    private void setUI(){
        emailInput = findViewById(R.id.editTextEmail);
        passwordInput = findViewById(R.id.editTextPassword);
        guestCheck = findViewById(R.id.checkBoxGuest);
        businessCheck = findViewById(R.id.checkBoxBusiness);
        signUpButton = findViewById(R.id.buttonSignUp);
    }

    // This method collects all inputs to it's corresponding variables and checks if they all have been filled in


    // This method checks if the email input is a valid email
    /*private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }*/



    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}