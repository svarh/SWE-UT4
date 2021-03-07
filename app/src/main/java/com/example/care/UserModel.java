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

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    public void login (String email, String password, boolean asGuest) {

        // Email is not valid
        if (!isEmailValid(email)) {
            setNotification(2);
            return;
        }

        // Password is not valid
        if (!isValid(password)) {
            setNotification(3);
            return;
        }

        // Username and password are ok, send to Credential
        Credential credential = new Credential(email, password, asGuest);
        credential.login();
    }

    // This function sets notification
    // int notificationType:
    //      1: Wrong email or passwords
    //      2: Email is invalid
    //      3: Password needs to have 6-12 characters, only contains letters and numbers.
    public void setNotification (int notificationType) {
        String notification = "";
        switch (notificationType) {
            case 1:
                notification = getString(R.string.wrong_email_or_password);
                break;

            case 2:
                notification = getString(R.string.email_invalid);
                break;

            case 3:
                notification = getString(R.string.password_invalid);
                break;
        }
        makeToast(notification);
    }
}
