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


public class Login extends AppCompatActivity implements View.OnClickListener {

    private static Activity thisActivity = null;

    private EditText emailInput;
    private EditText passwordInput;
    private CheckBox guestCheck;
    private CheckBox businessCheck;
    private Button logInButton;

    private String email;
    private String password;
    private boolean isGuess;

    private UserModel userModel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonLogIn:
                this.email = emailInput.getText().toString();
                this.password = passwordInput.getText().toString();
                this.userModel.login(this.email, this.password, this.isGuess);
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
        this.isGuess = true;
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
                this.isGuess = true;
            }
            this.emailInput.setHint("Email");
        });

        this.businessCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(this.businessCheck.isChecked()){
                this.guestCheck.setChecked(false);
                this.isGuess = false;
            }
            this.emailInput.setHint("Company Email");

        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}