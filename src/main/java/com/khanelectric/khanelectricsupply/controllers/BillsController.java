package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.models.Bill;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

/**
 * Controller class for displaying bill details in the BillsView.
 */
public class BillsController {

    @FXML
    private Label customerID;
    @FXML
    private Label customerName;
    @FXML
    private Label address;
    @FXML
    private Label meterNo;
    @FXML
    private Label status;
    @FXML
    private Label previousReading;
    @FXML
    private Label currentReading;
    @FXML
    private Label unitsConsumed;
    @FXML
    private Label dueDate;
    @FXML
    private Label hourlyCharge;
    @FXML
    private Label standingCharge;
    @FXML
    private Label vatPercentage;
    @FXML
    private Label amountPayable;

    // Formatter for date display
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Initializes the controller. It fetches and displays the details of the current bill for the user.
     */
    public void initialize() {
        // Assuming you want to load the bill for the current user
        DataStore dataStore = DataStore.getInstance();
        Bill currentBill = dataStore.getBills().get(0); // Fetch the current bill for the user

        // Populate fields with bill details
        customerID.setText(currentBill.getCustomerId());
        customerName.setText(currentBill.getCustomerName());
        address.setText(currentBill.getCustomerAddress());
        meterNo.setText(currentBill.getMeterNumber());
        status.setText(currentBill.getStatus());
        previousReading.setText(String.format("%.2f", currentBill.getPreviousReading()));
        currentReading.setText(String.format("%.2f", currentBill.getCurrentReading()));
        unitsConsumed.setText(String.format("%.2f", currentBill.getUnitsConsumed()));
        dueDate.setText(currentBill.getDueDate().format(dateFormatter));
        hourlyCharge.setText(String.format("£%.2f", currentBill.getHourlyCharge()));
        standingCharge.setText(String.format("£%.2f", currentBill.getStandingCharge()));
        vatPercentage.setText(String.format("%.2f%%", currentBill.getVatPercentage()));
        amountPayable.setText(String.format("£%.2f", currentBill.getAmountPayable()));
    }

}
