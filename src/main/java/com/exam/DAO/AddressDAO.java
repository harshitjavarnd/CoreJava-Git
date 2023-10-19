package com.exam.DAO;

public class AddressDAO {

    private String city;
    private String country;
    private String pin;

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPin() {
        return pin;
    }

    public AddressDAO(String city, String country, String pin) {
        this.city = city;
        this.country = country;
        this.pin = pin;
    }
}
