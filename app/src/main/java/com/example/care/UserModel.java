package com.example.care;

public class UserModel extends MainActivity {

    // This function check
    private boolean isValid (String str) {
        // Check to see if the length is valid (6 - 12 characters
        if (str.length() < 6 || str.length() > 12) {
            return false;
        }
        // Check to see if str has valid characters (a-z, A-Z, 0-9)
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z0-9]*$")));
    }

    public void login (String username, String password) {

        // Username is not valid
        if (!isValid(username)) {
            setNotification(2);
            return;
        }

        // Password is not valid
        if (!isValid(password)) {
            setNotification(3);
            return;
        }

        // TODO: Username and password are ok, send to Credential


    }

    // This function sets notification
    // int notificationType:
    //      1: Wrong username or passwords
    //      2: Username needs to have 6-12 characters, only contains letters and numbers.
    //      3: Password needs to have 6-12 characters, only contains letters and numbers.
    public void setNotification (int notificationType) {
        String notification = "";
        switch (notificationType) {
            case 1:
                notification = "Wrong username or passwords. Please try again!";
                break;

            case 2:
                notification = "Username needs to have 6-12 characters, only contains letters and numbers.";
                break;

            case 3:
                notification = "Password needs to have 6-12 characters, only contains letters and numbers.";
                break;
        }
        makeToast(notification);
    }
}
