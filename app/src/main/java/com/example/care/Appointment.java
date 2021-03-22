package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Appointment implements Parcelable {
    private String guestName;
    private String officerName;
    private String businessName;
    private int month;
    private int day;
    private int year;
    private int time;

    public Appointment(String guestName, String officerName, String businessName, int month, int day, int year, int time){
        this.guestName = guestName;
        this.officerName = officerName;
        this.businessName = businessName;
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
    }

    protected Appointment(Parcel in) {
        guestName = in.readString();
        officerName = in.readString();
        businessName = in.readString();
        month = in.readInt();
        day = in.readInt();
        year = in.readInt();
        time = in.readInt();
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getOfficerName() {
        return officerName;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "guestName='" + guestName + '\'' +
                ", officerName='" + officerName + '\'' +
                ", businessName='" + businessName + '\'' +
                ", month=" + month +
                ", day=" + day +
                ", year=" + year +
                ", time=" + time +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(guestName);
        dest.writeString(officerName);
        dest.writeString(businessName);
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeInt(year);
        dest.writeInt(time);
    }
}
