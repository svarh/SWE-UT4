package com.example.care;

public class Guest extends Credential {
    private String name;
    private String phone;

    public Guest(String email, String password, boolean asGuest) {
        super(email, password, asGuest);
    }

    public Guest (String email, String password, boolean asGuest, String name, String phone){
        super(email, password, asGuest);
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }
}
