package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UserModel userModel;
    static Activity thisActivity = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogIn:
                EditText editTxtUsername = findViewById(R.id.editTxtUsername);
                EditText editTxtPassword = findViewById(R.id.editTxtPassword);
                String username = editTxtUsername.getText().toString();
                String password = editTxtPassword.getText().toString();
                this.userModel.login(username, password);
                break;
        }
    }

    // This method initializes all the needed things
    private void initialize () {

        // Initialize Activity
        thisActivity = this;

        // Initialize UserModel
        userModel = new UserModel();

        // Initialize buttons listener
        Button btnLogin = findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(this);

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

}


