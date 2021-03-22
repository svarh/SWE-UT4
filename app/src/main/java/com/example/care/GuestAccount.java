package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;

public class GuestAccount implements Parcelable {
    private String email;
    private String password;
    private boolean asGuest;
    private String name;
    private String phone;

    public GuestAccount(String email, String password) {
        this.email = email;
        this.password = password;
        this.asGuest = true;
        this.name = null;
        this.phone = null;
    }

    public GuestAccount (String email, String password, String name, String phone){
        this.email = email;
        this.password = password;
        this.asGuest = true;
        this.name = name;
        this.phone = phone;
    }

    protected GuestAccount(Parcel in) {
        email = in.readString();
        password = in.readString();
        asGuest = in.readByte() != 0;
        name = in.readString();
        phone = in.readString();
    }

    public static final Creator<GuestAccount> CREATOR = new Creator<GuestAccount>() {
        @Override
        public GuestAccount createFromParcel(Parcel in) {
            return new GuestAccount(in);
        }

        @Override
        public GuestAccount[] newArray(int size) {
            return new GuestAccount[size];
        }
    };

    public String getPassword(){
        return password;
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

    public boolean isGuest(){
        return asGuest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeByte((byte) (asGuest ? 1 : 0));
        dest.writeString(name);
        dest.writeString(phone);
    }
}
