package com.switchfully.eurder.infrastructure.util;

import com.switchfully.eurder.domain.users.customers.PhoneNumber;

import java.util.regex.Pattern;

public final class ValidationUtil {
    public static boolean isNull(Object object) {
        return object == null;
    }

    public static void throwExceptionIfNull(Object object, String objectName) {
        if (isNull(object)) {
            throw new IllegalArgumentException(objectName + " cannot be null!");
        }
    }

    public static boolean isUUIDValid(String uuid) {
        return uuid.length() == 36;
    }

    public static boolean isEmailValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public static boolean isPhoneNumberValid(PhoneNumber phoneNumber) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phoneNumber.getPhoneNumber()).matches();
    }
}
