package com.khanelectric.khanelectricsupply.data_store;

import com.khanelectric.khanelectricsupply.models.Bill;
import com.khanelectric.khanelectricsupply.models.User;
import com.khanelectric.khanelectricsupply.utility.JDataHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This class manages the data store for users and bills.
 */
public class DataStore {
    public static final String USER_DATA_FILE = "data/users.jdata";
    public static final String BILL_DATA_FILE = "data/bills.jdata";
    private Map<String, User> users = new HashMap<>();
    private Map<String, Bill> bills = new HashMap<>();
    public User currentUser = null;

    private static DataStore instance = null;

    private DataStore() {
        loadUserData();
        loadBillData();
    }

    /**
     * Get the singleton instance of DataStore.
     *
     * @return The DataStore instance.
     */
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    // Load the user data from the serialization file
    public void loadUserData() {
        try {
            users = JDataHelper.loadDataFromFile(USER_DATA_FILE);
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions, possibly creating a new file if it doesn't exist
        }
    }

    // Load the bill data from the serialization file
    public void loadBillData() {
        try {
            bills = JDataHelper.loadDataFromFile(BILL_DATA_FILE);
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions, possibly creating a new file if it doesn't exist
        }
    }

    // Save the user data to the serialization file
    public void saveUserData() {
        try {
            JDataHelper.saveDataToFile(USER_DATA_FILE, users);
        } catch (IOException e) {
            // Handle exceptions
            System.out.println(e);
        }
    }

    // Save the bill data to the serialization file
    public void saveBillData() {
        try {
            JDataHelper.saveDataToFile(BILL_DATA_FILE, bills);
        } catch (IOException e) {
            // Handle exceptions
            System.out.println(e);
        }
    }

    // Add a new user
    public void addUser(User user) {
        users.put(user.getCustomerId(), user);
        saveUserData();
    }

    // Login method
    public User login(String customerId, String password) {
        User user = users.get(customerId);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    // Get all users
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    // Delete a user
    public void deleteUser(User user) {
        users.remove(user.getCustomerId());
        saveUserData();
    }

    // get all bills
    public List<Bill> getBills() {
        try {
            if (currentUser != null && currentUser.isAdmin()) {
                return new ArrayList<>(bills.values());
            } else {
                List<Bill> userBills = new ArrayList<>();
                for (Bill bill : bills.values()) {
                    if (bill.getCustomerId().equals(currentUser.getCustomerId())) {
                        userBills.add(bill);
                    }
                }
                return userBills;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(bills.values());
        }

    }

    // Add a new bill
    public void addBill(Bill bill) {
        bills.put(bill.getBillId(), bill);
        saveBillData();
    }

    // Get user by meter number
    public User getUserByMeterNumber(String meterNumber) {
        for (User user : users.values()) {
            if (user.getMeterNumber().equals(meterNumber)) {
                return user;
            }
        }
        return null;
    }

    // Function to get monthly sales data
    public Map<String, Double> getMonthlySalesData() {
        Map<String, Double> monthlySales = new TreeMap<>();
        for (Bill bill : bills.values()) {
            String monthYear = getMonthYearFromLocalDate(bill.getEndDate());
            monthlySales.putIfAbsent(monthYear, 0.0);
            monthlySales.put(monthYear, monthlySales.get(monthYear) + bill.getAmountPayable());
        }
        return monthlySales;
    }

    // Function to get total units used data
    public Map<String, Double> getTotalUnitsData() {
        Map<String, Double> totalUnits = new TreeMap<>();
        for (Bill bill : bills.values()) {
            String monthYear = getMonthYearFromLocalDate(bill.getEndDate());
            totalUnits.putIfAbsent(monthYear, 0.0);
            totalUnits.put(monthYear, totalUnits.get(monthYear) + bill.getUnitsConsumed());
        }
        return totalUnits;
    }

    // Function to get the ratio of paid/unpaid bills
    public Map<String, Integer> getPaidUnpaidData() {
        Map<String, Integer> paidUnpaid = new HashMap<>();
        paidUnpaid.put("PAID", 0);
        paidUnpaid.put("UNPAID", 0);

        for (Bill bill : bills.values()) {
            if ("PAID".equals(bill.getStatus())) {
                paidUnpaid.put("PAID", paidUnpaid.get("PAID") + 1);
            } else {
                paidUnpaid.put("UNPAID", paidUnpaid.get("UNPAID") + 1);
            }
        }
        return paidUnpaid;
    }

    // Helper method to format LocalDate to "Month Year"
    private String getMonthYearFromLocalDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
    }

    // Get Most Recent Bill
    public Bill getMostRecentBill() {
        // Most recent bill for the current user
        Bill mostRecentBill = null;
        for (Bill bill : bills.values()) {
            if (bill.getCustomerId().equals(currentUser.getCustomerId())) {
                if (mostRecentBill == null || bill.getEndDate().isAfter(mostRecentBill.getEndDate())) {
                    mostRecentBill = bill;
                }
            }
        }
        return mostRecentBill;
    }


    // Update Bill Status
    public void updateBillStatus(Bill bill) {
        bills.put(bill.getBillId(), bill);
        saveBillData();
    }
}
