package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UserModel userModel;
    static Activity thisActivity = null;
    private boolean asGuest = true;

    private Button btnGuest = null;
    private Button btnBusiness = null;
    private Button btnLogin = null;
    private Button btnSignUp = null;
    private EditText editTxtUsername = null;
    private EditText editTxtPassword = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
/*
            case R.id.btnGuest:
                this.asGuest = true;
                this.btnLogin.setText(getString(R.string.sign_in_as_guest));
                break;
*/

           /* case R.id.btnBusiness:
                this.asGuest = false;
                this.btnLogin.setText(getString(R.string.sign_in_as_business));
                break;*/

            case R.id.btnLogIn:
                /*String username = editTxtUsername.getText().toString();
                String password = editTxtPassword.getText().toString();
                this.userModel.login(username, password, asGuest);
                break;*/
                loginActivity();
                break;


            case R.id.btnSignUp:
                signUpActivity();
                break;
        }
    }

    // This method initializes all the needed things
    private void initialize () {

        // Initialize Activity
        thisActivity = this;

        // Initialize UserModel
        userModel = new UserModel();

        // Initialize Edit Text
        //this.editTxtUsername = findViewById(R.id.editTxtEmail);
        //this.editTxtPassword = findViewById(R.id.editTxtPassword);

        // Initialize buttons listener
       // this.btnGuest = findViewById(R.id.btnGuest);
       // this.btnGuest.setOnClickListener(this);
        //this.btnBusiness = findViewById(R.id.btnBusiness);
       // this.btnBusiness.setOnClickListener(this);
        this.btnLogin = findViewById(R.id.btnLogIn);
        this.btnLogin.setOnClickListener(this);
        this.btnSignUp = findViewById(R.id.btnSignUp);
        this.btnSignUp.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }


    // This method creates Toast to send notification
    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }

    private void loginActivity(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    private void signUpActivity(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

}


