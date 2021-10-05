package com.example.travelix.DBadapter;

public class BookingModel {
    private String roomType;
    private String guestName;
    private String guestPhone;
    private String guestEmail;
    private String checkIn;
    private String checkOut;
    private String tripType;
    private String Key;


    public BookingModel(String roomType, String guestName, String guestPhone, String guestEmail, String checkIn, String checkOut, String tripType, String Key) {
        this.roomType = roomType;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.guestEmail = guestEmail;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tripType = tripType;
        this.Key = Key;
    }

    public BookingModel(){

    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }



}
