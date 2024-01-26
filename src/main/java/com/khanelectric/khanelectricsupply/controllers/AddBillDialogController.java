package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.models.Bill;
import com.khanelectric.khanelectricsupply.models.User;
import com.khanelectric.khanelectricsupply.utility.FormValidator;
import com.khanelectric.khanelectricsupply.utility.Helpers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Controller class for the Add Bill Dialog.
 */
public class AddBillDialogController {

    @FXML
    private TextField meterNumberField;
    @FXML
    private TextField previousReadingField;
    @FXML
    private TextField currentReadingField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField vatPercentageField;
    @FXML
    private TextField standingChargeField;
    @FXML
    private TextField hourlyChargeField;
    @FXML
    private Label errorLabel;

    /**
     * Handle the cancel button click event.
     */
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) meterNumberField.getScene().getWindow();
        stage.close();
    }

    /**
     * Handle the add bill button click event.
     */
    @FXML
    private void handleAddBill() {
        if (validateInput()) {
            Bill newBill = new Bill();
            newBill.setBillId(Helpers.generateBillId());
            newBill.setMeterNumber(meterNumberField.getText());
            newBill.setPreviousReading(Double.parseDouble(previousReadingField.getText()));
            newBill.setCurrentReading(Double.parseDouble(currentReadingField.getText()));
            newBill.setStartDate(startDatePicker.getValue());
            newBill.setEndDate(endDatePicker.getValue());
            newBill.setUnitsConsumed(newBill.getCurrentReading() - newBill.getPreviousReading());

            // Calculate the number of days between start and end dates
            long days = java.time.temporal.ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue());

            // Retrieve customer data based on the meter number
            User customer = DataStore.getInstance().getUserByMeterNumber(meterNumberField.getText());
            if (customer != null) {
                newBill.setCustomerId(customer.getCustomerId());
                newBill.setCustomerName(customer.getFullName());
                newBill.setCustomerAddress(customer.getAddress());
            } else {
                // Handle case where no customer is found for the given meter number
                errorLabel.setText("No customer found for the given meter number.");
                return;
            }

            // Calculate amount payable
            double standingChargePerDay = Double.parseDouble(standingChargeField.getText());
            double hourlyCharge = Double.parseDouble(hourlyChargeField.getText());
            double vatPercentage = Double.parseDouble(vatPercentageField.getText());

            double standingChargeTotal = days * standingChargePerDay;
            double subtotal = hourlyCharge * (newBill.getCurrentReading() - newBill.getPreviousReading());
            double totalBeforeVat = subtotal + standingChargeTotal;
            double totalWithVat = totalBeforeVat + (totalBeforeVat * vatPercentage / 100);

            newBill.setAmountPayable(totalWithVat);

            // Set due date to one month after the end date
            newBill.setDueDate(newBill.getEndDate().plusMonths(1));

            // Set status to UNPAID
            newBill.setStatus("UNPAID");
            newBill.setVatPercentage(vatPercentage);
            newBill.setStandingCharge(standingChargePerDay);
            newBill.setHourlyCharge(hourlyCharge);

            DataStore.getInstance().addBill(newBill);

            // Close the dialog
            Stage stage = (Stage) meterNumberField.getScene().getWindow();
            stage.close();
        } else {
            errorLabel.setText("Missing or invalid input");
        }
    }

    /**
     * Validate the input fields before adding a new bill.
     *
     * @return true if all input is valid, false otherwise.
     */
    private boolean validateInput() {
        return FormValidator.isNotEmpty(meterNumberField.getText()) && FormValidator.isNotEmpty(previousReadingField.getText()) && FormValidator.isNotEmpty(currentReadingField.getText()) && startDatePicker.getValue() != null && endDatePicker.getValue() != null && FormValidator.isNumeric(vatPercentageField.getText()) && FormValidator.isNumeric(standingChargeField.getText()) && FormValidator.isNumeric(hourlyChargeField.getText());
    }
}
