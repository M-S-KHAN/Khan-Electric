package com.khanelectric.khanelectricsupply.utility;

import java.util.regex.Pattern;

/**
 * Utility class for validating form input fields.
 */
public class FormValidator {

    /**
     * Checks if a text is not null and not empty (after trimming).
     *
     * @param text The text to be checked.
     * @return True if the text is not null and not empty; otherwise, false.
     */
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     * Checks if an email address is valid based on a regular expression.
     *
     * @param email The email address to be checked.
     * @return True if the email address is valid; otherwise, false.
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    /**
     * Checks if a phone number is valid based on a regular expression.
     *
     * @param number The phone number to be checked.
     * @return True if the phone number is valid; otherwise, false.
     */
    public static boolean isValidPhoneNumber(String number) {
        // Regular expression for phone number validation
        String phoneRegex = "^[+]?[0-9]{10,13}$";
        return number != null && Pattern.compile(phoneRegex).matcher(number).matches();
    }

    /**
     * Checks if a string can be parsed as a numeric value.
     *
     * @param number The string to be checked.
     * @return True if the string can be parsed as a numeric value; otherwise, false.
     */
    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a text has a specific length.
     *
     * @param text   The text to be checked.
     * @param length The expected length of the text.
     * @return True if the text has the specified length; otherwise, false.
     */
    public static boolean hasLength(String text, int length) {
        return text != null && text.length() == length;
    }

    /**
     * Checks if a text has a minimum length.
     *
     * @param text     The text to be checked.
     * @param minLength The minimum length required for the text.
     * @return True if the text has at least the minimum length; otherwise, false.
     */
    public static boolean hasMinLength(String text, int minLength) {
        return text != null && text.length() >= minLength;
    }

    /**
     * Checks if a text has a maximum length.
     *
     * @param text     The text to be checked.
     * @param maxLength The maximum length allowed for the text.
     * @return True if the text has at most the maximum length; otherwise, false.
     */
    public static boolean hasMaxLength(String text, int maxLength) {
        return text != null && text.length() <= maxLength;
    }
}
