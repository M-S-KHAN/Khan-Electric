package com.khanelectric.khanelectricsupply.models;

import java.time.LocalDate;

public class Bill implements java.io.Serializable {
    private String billId;
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String meterNumber;
    private double previousReading;
    private double currentReading;
    private double unitsConsumed;
    private double amountPayable;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dueDate;
    private String status;
    private double vatPercentage;
    private double standingCharge;
    private double hourlyCharge;

    // Constructors
    public Bill() {
    }

    // Add a constructor with parameters
    public Bill(String billId, String customerId, String customerName, String customerAddress, String meterNumber, double previousReading, double currentReading, double unitsConsumed, double amountPayable, LocalDate startDate, LocalDate endDate, LocalDate dueDate, String status, double vatPercentage, double standingCharge, double hourlyCharge) {
        this.billId = billId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.meterNumber = meterNumber;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.unitsConsumed = unitsConsumed;
        this.amountPayable = amountPayable;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.status = status;
        this.vatPercentage = vatPercentage;
        this.standingCharge = standingCharge;
        this.hourlyCharge = hourlyCharge;
    }

    // Getters and Setters
    // For each field, generate a getter and setter like this:
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public double getPreviousReading() {
        return previousReading;
    }

    public void setPreviousReading(double previousReading) {
        this.previousReading = previousReading;
    }

    public double getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(double currentReading) {
        this.currentReading = currentReading;
    }

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(double vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public double getStandingCharge() {
        return standingCharge;
    }

    public void setStandingCharge(double standingCharge) {
        this.standingCharge = standingCharge;
    }

    public double getHourlyCharge() {
        return hourlyCharge;
    }

    public void setHourlyCharge(double hourlyCharge) {
        this.hourlyCharge = hourlyCharge;
    }

    // Add a toString() method
    @Override
    public String toString() {
        return "Bill{" + "billId='" + billId + '\'' + ", customerId='" + customerId + '\'' + ", customerName='" + customerName + '\'' + ", customerAddress='" + customerAddress + '\'' + ", meterNumber='" + meterNumber + '\'' + ", previousReading=" + previousReading + ", currentReading=" + currentReading + ", unitsConsumed=" + unitsConsumed + ", amountPayable=" + amountPayable + ", startDate=" + startDate + ", endDate=" + endDate + ", dueDate=" + dueDate + ", status='" + status + '\'' + ", vatPercentage=" + vatPercentage + ", standingCharge=" + standingCharge + ", hourlyCharge=" + hourlyCharge + '}';
    }
}

