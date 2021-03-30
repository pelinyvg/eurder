package com.switchfully.eurder.domain;

public class PhoneNumber {
    private final int countryCode;
    private final int number;

    public PhoneNumber(int countryCode, int number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return "+" + countryCode + " " + number;
    }
}
