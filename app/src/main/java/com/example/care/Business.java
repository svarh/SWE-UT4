package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;

public class Business implements Parcelable {
    private String companyEmail;
    private String companyName;
    private String password;
    private boolean asGuest;
    private String phone;

    public Business(String companyEmail, String password) {
        this.companyEmail = companyEmail;
        this.password = password;
        this.asGuest = false;
        this.companyName = null;
        this.phone = null;
    }

    public Business(String companyEmail, String password, String companyName, String phone){
        this.companyEmail = companyEmail;
        this.password = password;
        this.asGuest = false;
        this.companyName = companyName;
        this.phone = phone;
    }


    protected Business(Parcel in) {
        companyEmail = in.readString();
        companyName = in.readString();
        password = in.readString();
        asGuest = in.readByte() != 0;
        phone = in.readString();
    }

    public static final Creator<Business> CREATOR = new Creator<Business>() {
        @Override
        public Business createFromParcel(Parcel in) {
            return new Business(in);
        }

        @Override
        public Business[] newArray(int size) {
            return new Business[size];
        }
    };

    public String getPassword(){
        return password;
    }

    public void getCompanyName(String companyName){
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setPhone(String phone){
        this.phone = phone;
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
        dest.writeString(companyEmail);
        dest.writeString(companyName);
        dest.writeString(password);
        dest.writeByte((byte) (asGuest ? 1 : 0));
        dest.writeString(phone);
    }
}
