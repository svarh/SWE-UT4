package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Appointment implements Parcelable {
    private String guestName;
    private String officerName;
    private String businessName;
    private Date date;
    private int time;

    public Appointment(String guestName, String officerName, String businessName, Date date, int time){
        this.guestName = guestName;
        this.officerName = officerName;
        this.businessName = businessName;
        this.date = date;
        this.time = time;
    }

    protected Appointment(Parcel in) {
        guestName = in.readString();
        officerName = in.readString();
        businessName = in.readString();
        date = in.readParcelable(Date.class.getClassLoader());
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

    public void setDate(Date date) {
        this.date = date;
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

    public Date getDate() {
        return date;
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
                ", date=" + date +
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
        dest.writeParcelable(date, flags);
        dest.writeInt(time);
    }
}
