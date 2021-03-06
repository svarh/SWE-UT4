package com.example.care;

public class Credential {
    private String username;
    private String password;
    private boolean asGuest; // true for Guest, false for Business
    private boolean isLogedIn;

    public Credential () {
        username = "";
        password = "";
        asGuest = true;
        isLogedIn = false;

    }

    // This function send username and password to database to log in
    public void login () {
        // TODO: Send username and password to database
    }

    // This function is called by database when log in failed (wrong username or password)
    public void loginFailed () {
        // Notify user that log in has failed
        UserModel um = new UserModel();
        um.setNotification(1);
    }

}