package com.example.care;

public class Tuple {
    private int time;
    private boolean available;

    public Tuple (int time, boolean available){
        this.time = time;
        this.available = available;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getTime() {
        return time;
    }

    public boolean isAvailable() {
        return available;
    }
}
