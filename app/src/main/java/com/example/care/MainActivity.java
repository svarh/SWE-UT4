package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static Activity thisActivity = null;

    private Button btnLogin = null;
    private Button btnSignUp = null;

    private UserModel userModel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLogIn:
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

        userModel = new UserModel(this);
        userModel.hideStatusBar();

        // Initialize buttons
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


