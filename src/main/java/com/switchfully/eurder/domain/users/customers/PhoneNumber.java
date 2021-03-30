package com.switchfully.eurder.domain.users.customers;

public class PhoneNumber {
    private final int countryCode;
    private final String number;

    public PhoneNumber(int countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return "+" + countryCode + number;
    }
}
