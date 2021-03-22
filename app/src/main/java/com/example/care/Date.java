package com.example.care;

import android.os.Parcel;
import android.os.Parcelable;

public class Date implements Parcelable {
    public int month;
    public int day;
    public int year;

    public Date (int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    protected Date(Parcel in) {
        month = in.readInt();
        day = in.readInt();
        year = in.readInt();
    }

    public static final Creator<Date> CREATOR = new Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel in) {
            return new Date(in);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Date{" + "Month=" + month + ", Day=" + day + ", Year=" + year + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeInt(year);
    }
}
