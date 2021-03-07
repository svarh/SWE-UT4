package com.example.care;

public class Guest extends Credential {
    private String name;
    private String phone;

    public Guest(String email, String password, boolean asGuest) {
        super(email, password, asGuest);
    }
}
