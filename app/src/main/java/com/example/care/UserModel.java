package com.example.care;

import android.app.Activity;
import android.widget.Toast;


public class UserModel{

    private Activity activity;

    public UserModel (Activity calledFrom) {
        this.activity = calledFrom;
    }

    // This function check
    private boolean isValidPass (String str) {
        // Check to see if the length is valid (6 - 12 characters)
        if (str.length() < 6 || str.length() > 12) {
            return false;
        }
        // Check to see if str has valid characters (a-z, A-Z, 0-9)
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z0-9]*$")));
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }
    // This function sets notification
    // int notificationType:
    //      1: Wrong email or passwords
    //      2: Email is invalid
    //      3: Password needs to have 6-12 characters, only contains letters and numbers.
    private void setNotification (int notificationType) {
        String notification = "";
        switch (notificationType) {
            case 1:
                notification = this.activity.getString(R.string.wrong_email_or_password);
                break;

            case 2:
                notification = this.activity.getString(R.string.email_invalid);
                break;

            case 3:
                notification = this.activity.getString(R.string.password_invalid);
                break;

            case 4:
                notification = this.activity.getString(R.string.password_not_match);
                break;

            case 5:
                notification = this.activity.getString(R.string.phone_invalid);
                break;

            case 6:
                notification = "Passwords do not match";
                break;
        }
        Toast.makeText(this.activity, notification, Toast.LENGTH_SHORT).show();
    }

    public void makeToast(String str) {
        Toast.makeText(this.activity, str, Toast.LENGTH_SHORT).show();
    }

    public boolean login (String email, String password) {

        // Email is not valid
        if (!isEmailValid(email)) {
            setNotification(2);
            return false;
        }

        // Password is not valid
        if (!isValidPass(password)) {
            setNotification(3);
            return false;
        }

        return true;
    }

    public boolean signUp (String email, String password, String confirmPass, String phone){
        //Email is not valid
        if(!isEmailValid(email)){
            setNotification(2);
            return false;
        }

        //Password is not valid
        if (!isValidPass(password)){
            setNotification(3);
            return false;
        }

        //Passwords do not match
        if(!password.equals(confirmPass)){
            setNotification(6);
            return false;
        }

        //Phone number is not valid
        if(phone.length() != 10){
            setNotification(5);
            return false;
        }

        return true;
    }

}
