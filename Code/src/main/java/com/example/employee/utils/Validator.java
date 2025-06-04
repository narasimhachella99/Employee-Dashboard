package com.example.employee.utils;

import java.util.regex.Pattern;

public class Validator {

    private static final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])([a-zA-Z0-9@$!%*?&]{8,})$";
    private static final String emailPattern = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";

    public static boolean validatePassword(String password) {
        return Pattern.matches(passwordPattern, password);
    }

    public static boolean validateEmail(String email) {
        return Pattern.matches(emailPattern, email);
    }
}
