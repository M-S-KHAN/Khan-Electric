package com.khanelectric.khanelectricsupply.models;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerId;
    private String fullName;
    private String address;
    private String meterNumber;
    private String contactNumber;
    private boolean isAdmin;

    private String password;
    private String email;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String customerId, String password, String fullName, String address, String meterNumber, String contactNumber, boolean isAdmin, String email) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.address = address;
        this.meterNumber = meterNumber;
        this.contactNumber = contactNumber;
        this.isAdmin = isAdmin;
        this.password = password;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "customerId='" + customerId + '\'' + ", fullName='" + fullName + '\'' + ", address='" + address + '\'' + ", meterNumber='" + meterNumber + '\'' + ", contactNumber='" + contactNumber + '\'' + ", isAdmin=" + isAdmin + '}';
    }
}
