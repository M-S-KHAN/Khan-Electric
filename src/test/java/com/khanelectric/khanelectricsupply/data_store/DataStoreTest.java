package com.khanelectric.khanelectricsupply.data_store;

import com.khanelectric.khanelectricsupply.models.Bill;
import com.khanelectric.khanelectricsupply.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataStoreTest {
    private DataStore dataStore;

    @BeforeEach
    void setUp() {
        dataStore = DataStore.getInstance();

        // Set up test data
        User user  = new User("001", "123123", "John Doe", "123 Main St", "12345", "00004444", false, "");
        dataStore.addUser(user);

        Bill bill = new Bill("B001", "001", "John Doe", "123 Main St", "12345", 100.0, 150.0, 50.0, 200.0, LocalDate.now().minusMonths(1), LocalDate.now(), LocalDate.now().plusMonths(1), "UNPAID", 20.0, 15.0, 2.0);
        dataStore.addBill(bill);
    }

    @Test
    void addUser_ShouldAddUser() {
        User newUser = new User("001", "123123", "John Doe", "123 Main St", "12345", "00004444", false, "");
        dataStore.addUser(newUser);
        assertNotNull(dataStore.login("002", "password456"));
    }

    @Test
    void deleteUser_ShouldRemoveUser() {
        User user = dataStore.login("00001", "123456");
        assertNotNull(user);
        dataStore.deleteUser(user);
        assertNull(dataStore.login("001", "password123"));
    }

    @Test
    void getBills_ShouldReturnAllBills() {
        List<Bill> bills = dataStore.getBills();
        assertFalse(bills.isEmpty());
    }

    @Test
    void addBill_ShouldAddBill() {
        Bill newBill = new Bill("B002", "001", "John Doe", "123 Main St", "12345", 200.0, 250.0, 50.0, 400.0, LocalDate.now().minusMonths(2), LocalDate.now().minusMonths(1), LocalDate.now(), "UNPAID", 20.0, 15.0, 2.0);
        dataStore.addBill(newBill);
        assertEquals(2, dataStore.getBills().size());
    }

    @Test
    void login_ValidCredentials_ShouldReturnUser() {
        User user = dataStore.login("00001", "123456");
        assertNotNull(user);
        assertEquals("Muhammad Sajawal Khan", user.getFullName());
    }

    @Test
    void login_InvalidCredentials_ShouldReturnNull() {
        User user = dataStore.login("001", "wrongpassword");
        assertNull(user);
    }

    @Test
    void getMonthlySalesData_ShouldReturnCorrectData() {
        Map<String, Double> salesData = dataStore.getMonthlySalesData();
        assertFalse(salesData.isEmpty());
        assertEquals(708.595, salesData.get(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy"))));
    }

    @Test
    void getTotalUnitsData_ShouldReturnCorrectData() {
        Map<String, Double> unitsData = dataStore.getTotalUnitsData();
        assertFalse(unitsData.isEmpty());
        assertEquals(271.0, unitsData.get(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy"))));
    }
}
