package com.example.care;

import java.util.ArrayList;
import java.util.HashMap;

public class Officer {
    private String name;
    private String business;
    private HashMap<String, ArrayList<Tuple>> appointments;

    public Officer(String first, String last, String business){
        this.name = first + " " + last;
        this.business = business;
        appointments = new HashMap<>();
        ArrayList<Tuple> list = new ArrayList<>();
        list.add(new Tuple(9, true));
        list.add(new Tuple(10, true));
        list.add(new Tuple(11, true));
        list.add(new Tuple(12, true));
        list.add(new Tuple(1, true));
        list.add(new Tuple(2, true));
        list.add(new Tuple(3, true));
        list.add(new Tuple(4, true));
        appointments.put("04/26/2021", list);
        appointments.put("04/27/2021", list);
        appointments.put("04/28/2021", list);
        appointments.put("04/29/2021", list);
        appointments.put("04/30/2021", list);

    }

    public Officer(String first, String last, String business, HashMap<String, ArrayList<Tuple>> appointments){
        this.name = first + " " + last;
        this.business = business;
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public String getBusiness() {
        return business;
    }

    public HashMap<String, ArrayList<Tuple>> getAppointments() {
        return appointments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppointments(HashMap<String, ArrayList<Tuple>> appointments) {
        this.appointments = appointments;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public boolean getAvailability(String date, int time){
        ArrayList<Tuple> list;
        list = appointments.get(date);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getTime() == time){
                return list.get(i).isAvailable();
            }
        }
        return false;
    }
}
