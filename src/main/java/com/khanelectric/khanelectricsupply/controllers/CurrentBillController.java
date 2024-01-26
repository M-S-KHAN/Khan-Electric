package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.models.Bill;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controller class for displaying the current bill in the CurrentBillView.
 */
public class CurrentBillController {

    @FXML
    private VBox currentBillContainer;
    @FXML
    private Label customerID;
    @FXML
    private Label customerName;
    @FXML
    private Label address;
    @FXML
    private Label meterNo;
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
    @FXML
    private Button payBillButton;

    private Bill currentBill;

    /**
     * Initializes the controller. Loads and displays the current bill for the user.
     */
    public void initialize() {
        // Load the current bill for the user
        loadCurrentBill();
    }

    /**
     * Loads the details of the current bill and populates the view with the bill's information.
     */
    private void loadCurrentBill() {
        // Assuming there's a method to get the current user's bill
        currentBill = DataStore.getInstance().getMostRecentBill();
        // Populate the bill details into the view
        customerID.setText(currentBill.getCustomerId());
        customerName.setText(currentBill.getCustomerName());
        address.setText(currentBill.getCustomerAddress());
        meterNo.setText(currentBill.getMeterNumber());
        previousReading.setText(String.valueOf(currentBill.getPreviousReading()));
        currentReading.setText(String.valueOf(currentBill.getCurrentReading()));
        unitsConsumed.setText(String.valueOf(currentBill.getUnitsConsumed()));
        dueDate.setText(currentBill.getDueDate().toString());
        hourlyCharge.setText(String.valueOf(currentBill.getHourlyCharge()));
        standingCharge.setText(String.valueOf(currentBill.getStandingCharge()));
        vatPercentage.setText(String.valueOf(currentBill.getVatPercentage()));
        amountPayable.setText(String.valueOf(currentBill.getAmountPayable()));
        // Disable the pay bill button if the bill is already paid
        if ("PAID".equals(currentBill.getStatus())) {
            payBillButton.setDisable(true);
        }
    }

    /**
     * Handles the "Pay Bill" button click event. If the bill is not already paid, it updates the bill status to "PAID".
     */
    @FXML
    private void handlePayBill() {
        if (currentBill != null && !"PAID".equals(currentBill.getStatus())) {
            currentBill.setStatus("PAID");
            // Update the bill in the data store
            DataStore.getInstance().updateBillStatus(currentBill);
            // Update UI to reflect payment
            payBillButton.setDisable(true);
            // Optionally, show a confirmation message
            loadCurrentBill();
        }
    }
}
