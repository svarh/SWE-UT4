package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class GuestAccount implements Parcelable {
    private String email;
    private String password;
    private boolean asGuest;
    private String name;
    private String phone;
    private ArrayList<HashMap<String, String>> appointments;
    private HashMap<Date, Boolean> covidResults;

    public GuestAccount(String email, String password) {
        this.email = email;
        this.password = password;
        this.asGuest = true;
        this.name = null;
        this.phone = null;
        this.appointments = new ArrayList<>();
        this.covidResults = new HashMap<>();
    }

    public GuestAccount (String email, String password, String name, String phone){
        this.email = email;
        this.password = password;
        this.asGuest = true;
        this.name = name;
        this.phone = phone;
        this.appointments = new ArrayList<HashMap<String, String>>();
        this.covidResults = new HashMap<>();
    }

    public GuestAccount (String email, String password, String name, String phone, ArrayList<HashMap<String, String>> appointments){
        this.email = email;
        this.password = password;
        this.asGuest = true;
        this.name = name;
        this.phone = phone;
        this.appointments = appointments;
        this.covidResults = new HashMap<>();
    }

    protected GuestAccount(Parcel in) {
        email = in.readString();
        password = in.readString();
        asGuest = in.readByte() != 0;
        name = in.readString();
        phone = in.readString();
        appointments = in.readArrayList(ClassLoader.getSystemClassLoader());
        covidResults = in.readHashMap(ClassLoader.getSystemClassLoader());

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public boolean isGuest(){
        return asGuest;
    }

    public void setAppointments(ArrayList<HashMap<String, String>> appointments) {
        this.appointments = appointments;
    }

    public ArrayList<HashMap<String, String>> getAppointments() {
        return appointments;
    }

    public void addAppointment(HashMap<String, String> appointment){
        appointments.add(appointment);
    }

    public void makeAppointment(String organization, String officer, String guestName, String date, String time, String confirmation){
        HashMap<String, String> appointment = new HashMap<String, String>(){{
            put("Organization", organization);
            put("Officer", officer);
            put("Guest", guestName);
            put("Date", date);
            put("Time", time);
            put("Confirmation", confirmation);
        }};

        appointments.add(appointment);
    }

    public void setCovidResults(HashMap<Date, Boolean> covidResults) {
        this.covidResults = covidResults;
    }

    public HashMap<Date, Boolean> getCovidResults() {
        return covidResults;
    }

    public int getAppointmentSize(){
        return appointments.size();
    }

    public void addCovidResult(Date date, boolean passed){
        covidResults.put(date, passed);
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
        dest.writeList(appointments);
        dest.writeMap(covidResults);
    }
}
