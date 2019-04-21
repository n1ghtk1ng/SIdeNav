package com.example.sidenav;

public class Event {
    private String address, latitude,longitude, date, name, organiserId, time, id;

    public Event(String address, String latitude, String longitude, String date, String name, String organizerId, String time, String id) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.name = name;
        this.organiserId = organizerId;
        this.time = time;
        this.id = id;
    }

    public Event() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizerId() {
        return organiserId;
    }

    public void setOrganizerId(String organizerId) {
        this.organiserId = organizerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
