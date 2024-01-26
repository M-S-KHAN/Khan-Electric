package com.khanelectric.khanelectricsupply.utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormValidatorTest {

    @Test
    void isNotEmpty_ShouldReturnTrueForNonEmptyString() {
        assertTrue(FormValidator.isNotEmpty("Hello"));
    }

    @Test
    void isNotEmpty_ShouldReturnFalseForEmptyString() {
        assertFalse(FormValidator.isNotEmpty(" "));
        assertFalse(FormValidator.isNotEmpty(""));
    }

    @Test
    void isNotEmpty_ShouldReturnFalseForNull() {
        assertFalse(FormValidator.isNotEmpty(null));
    }

    @Test
    void isValidEmail_ShouldReturnTrueForValidEmail() {
        assertTrue(FormValidator.isValidEmail("test@example.com"));
    }

    @Test
    void isValidEmail_ShouldReturnFalseForInvalidEmail() {
        assertFalse(FormValidator.isValidEmail("test@example"));
        assertFalse(FormValidator.isValidEmail("testexample.com"));
        assertFalse(FormValidator.isValidEmail("test@.com"));
    }

    @Test
    void isValidEmail_ShouldReturnFalseForNull() {
        assertFalse(FormValidator.isValidEmail(null));
    }

    @Test
    void isValidPhoneNumber_ShouldReturnTrueForValidNumber() {
        assertTrue(FormValidator.isValidPhoneNumber("1234567890"));
        assertTrue(FormValidator.isValidPhoneNumber("+1234567890123"));
    }

    @Test
    void isValidPhoneNumber_ShouldReturnFalseForInvalidNumber() {
        assertFalse(FormValidator.isValidPhoneNumber("12345"));
        assertFalse(FormValidator.isValidPhoneNumber("abc1234567"));
        assertFalse(FormValidator.isValidPhoneNumber("12345678901234"));  // Too long
    }

    @Test
    void isNumeric_ShouldReturnTrueForNumericString() {
        assertTrue(FormValidator.isNumeric("123"));
        assertTrue(FormValidator.isNumeric("123.45"));
    }

    @Test
    void isNumeric_ShouldReturnFalseForNonNumericString() {
        assertFalse(FormValidator.isNumeric("abc"));
        assertFalse(FormValidator.isNumeric("12abc34"));
    }

    @Test
    void hasLength_ShouldReturnTrueForCorrectLength() {
        assertTrue(FormValidator.hasLength("12345", 5));
    }

    @Test
    void hasLength_ShouldReturnFalseForIncorrectLength() {
        assertFalse(FormValidator.hasLength("1234", 5));
        assertFalse(FormValidator.hasLength("123456", 5));
    }

    @Test
    void hasMinLength_ShouldReturnTrueForSufficientLength() {
        assertTrue(FormValidator.hasMinLength("12345", 5));
        assertTrue(FormValidator.hasMinLength("123456", 5));
    }

    @Test
    void hasMinLength_ShouldReturnFalseForInsufficientLength() {
        assertFalse(FormValidator.hasMinLength("1234", 5));
    }

    @Test
    void hasMaxLength_ShouldReturnTrueForAcceptableLength() {
        assertTrue(FormValidator.hasMaxLength("12345", 5));
        assertTrue(FormValidator.hasMaxLength("1234", 5));
    }

    @Test
    void hasMaxLength_ShouldReturnFalseForExcessiveLength() {
        assertFalse(FormValidator.hasMaxLength("123456", 5));
    }
}
