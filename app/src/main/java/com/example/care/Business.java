package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Business implements Parcelable {
    private String companyName;
    private ArrayList<String> officers;

    public Business(String companyName){
        this.companyName = companyName;
        this.officers = new ArrayList<>();
    }

    public Business(String companyName, ArrayList officers){
        this.companyName = companyName;
        this.officers = officers;
    }

    protected Business(Parcel in) {
        companyName = in.readString();
        officers = in.createStringArrayList();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyName);
        dest.writeStringList(officers);
    }
}
