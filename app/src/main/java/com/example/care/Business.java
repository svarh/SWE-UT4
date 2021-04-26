package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Business implements Parcelable {
    private String companyName;
    private ArrayList<String> officers;
    private ArrayList<HashMap<String, String>> appointments;

    public Business(String companyName){
        this.companyName = companyName;
        this.officers = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public Business(String companyName, ArrayList officers){
        this.companyName = companyName;
        this.officers = officers;
        this.appointments = new ArrayList<>();
    }

    public Business(String companyName, ArrayList officers, ArrayList<HashMap<String, String>> appointments){
        this.companyName = companyName;
        this.officers = officers;
        this.appointments = appointments;
    }

    protected Business(Parcel in) {
        companyName = in.readString();
        officers = in.createStringArrayList();
        appointments = in.readArrayList(ClassLoader.getSystemClassLoader());
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

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setOfficers(ArrayList<String> officers){
        this.officers = officers;
    }

    public int getOfficerSize(){
        if(officers == null){
            return -1;
        }
        return officers.size();
    }

    public String getOfficer(int i){
        return officers.get(i);
    }

    public boolean addOfficer(String first, String last){
        String s = first + " " + last;
        if(officers.contains(s)){
            return false;
        }
        else{
            officers.add(s);
            return true;
        }
    }

    public void removeOfficer(String first, String last){
        String s = first + " " + last;
        for(int i = 0; i < officers.size(); i++){
            if(officers.get(i).equals(s)){
                officers.remove(i);
            }
        }
    }

    public ArrayList<String> getOfficers(){
        if(officers == null){
            return null;
        }
        return officers;
    }

    public ArrayList<HashMap<String, String>> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<HashMap<String, String>> appointments) {
        this.appointments = appointments;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyName);
        dest.writeStringList(officers);
        dest.writeList(appointments);
    }
}
