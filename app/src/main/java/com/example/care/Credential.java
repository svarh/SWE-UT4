package com.example.care;

public class Credential {
    private String email;
    private String password;
    private boolean asGuest; // true for Guest, false for Business
    private boolean isLogedIn;

    public Credential (String email, String password, boolean asGuest) {
        this.email = email;
        this.password = password;
        this.asGuest = asGuest;
        this.isLogedIn = false;

    }

    // This function send email and password to database to log in
    public void login () {
        // TODO: Send email and password to database
    }

    // This function is called by database when log in failed (wrong username or password)
    public void loginFailed () {
        // Notify user that log in has failed
        UserModel um = new UserModel();
        um.setNotification(1);
    }

}