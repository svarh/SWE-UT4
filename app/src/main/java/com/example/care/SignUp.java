package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
    EditText companyNameInput;
    CheckBox guestCheck;
    CheckBox businessCheck;
    Button signUpButton;

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
                phoneInput.setVisibility(View.VISIBLE);
                companyNameInput.setVisibility(View.INVISIBLE);
            }
        });
        businessCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(businessCheck.isChecked()){
                    guestCheck.setChecked(false);
                }
                nameInput.setVisibility(View.VISIBLE);
                nameInput.setHint("Receptionist Name");
                emailInput.setHint("Company Email");
                phoneInput.setVisibility(View.VISIBLE);
                companyNameInput.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setUI(){
        emailInput = findViewById(R.id.editTextEmail);
        passwordInput = findViewById(R.id.editTextPassword);
        confirmPasswordInput = findViewById(R.id.editTextConfirmPassword);
        nameInput = findViewById(R.id.editTextName);
        phoneInput = findViewById(R.id.editTextPhoneNumb);
        companyNameInput = findViewById(R.id.editTextCompanyName);
        guestCheck = findViewById(R.id.checkBoxGuest);
        businessCheck = findViewById(R.id.checkBoxBusiness);
        signUpButton = findViewById(R.id.buttonSignUp);
    }

    // This method creates Toast to send notification
    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }
}