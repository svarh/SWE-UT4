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

public class SignUp extends AppCompatActivity {

    EditText emailInput;
    EditText passwordInput;
    EditText confirmPasswordInput;
    EditText nameInput;
    EditText phoneInput;
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
        setContentView(R.layout.activity_sign_up);

        setUI();
        thisActivity = this;

        guestCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(guestCheck.isChecked()){
                    businessCheck.setChecked(false);
                }
                emailInput.setHint("Email");
                nameInput.setVisibility(View.VISIBLE);
                nameInput.setHint("Name");
            }
        });

        businessCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(businessCheck.isChecked()){
                    guestCheck.setChecked(false);
                }
                emailInput.setHint("Company Email");
                nameInput.setVisibility(View.VISIBLE);
                nameInput.setHint("Company Name");
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    // This method sets up the UI
    private void setUI(){
        emailInput = findViewById(R.id.editTextEmail);
        passwordInput = findViewById(R.id.editTextPassword);
        confirmPasswordInput = findViewById(R.id.editTextConfirmPassword);
        nameInput = findViewById(R.id.editTextName);
        phoneInput = findViewById(R.id.editTextPhoneNumb);
        guestCheck = findViewById(R.id.checkBoxGuest);
        businessCheck = findViewById(R.id.checkBoxBusiness);
        signUpButton = findViewById(R.id.buttonSignUp);
    }

    // This method collects all inputs to it's corresponding variables and checks if they all have been filled in
    private boolean checkInputs(String type){
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        name = nameInput.getText().toString();
        phoneNumb = phoneInput.getText().toString();
        if(email.length() == 0 | password.length() == 0 | name.length() == 0 | phoneNumb.length() == 0){
            makeToast("Please fill in all available fields");
            return false;
        }
        return true;
    }

    // This method checks if the email input is a valid email
    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    // This method checks if the inputs for password, email, and phone number are valid
    private boolean checkValidity(String password, String email, String phoneNumb){
        if (password.length() < 6 | password.length() > 12) {
            makeToast("Password must be between 6 - 12 characters");
            return false;
        } else if (!password.equals(confirmPasswordInput.getText().toString())) {
            makeToast("Passwords do not match");
            return false;
        }
        if (!isEmailValid(email)) {
            makeToast("Email is invalid");
            return false;
        }
        if (phoneNumb.length() != 10) {
            makeToast("Phone number is invalid");
            return false;
        }
        return true;
    }


    // This method creates Toast to send notification
    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}