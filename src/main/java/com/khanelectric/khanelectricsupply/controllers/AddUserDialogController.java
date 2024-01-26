package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.models.User;
import com.khanelectric.khanelectricsupply.utility.FormValidator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controller class for the Add User Dialog.
 */
public class AddUserDialogController {

    @FXML
    private TextField customerIdField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField meterNumberField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox isAdminCheckBox;
    @FXML
    private Label errorLabel;

    /**
     * Handle the cancel button click event.
     */
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) customerIdField.getScene().getWindow();
        stage.close();
    }

    /**
     * Handle the add user button click event.
     */
    @FXML
    private void handleAddUser() {
        if (validateInput()) {
            User newUser = new User(customerIdField.getText(), passwordField.getText(), fullNameField.getText(), addressField.getText(), meterNumberField.getText(), contactNumberField.getText(), isAdminCheckBox.isSelected(), emailField.getText());
            DataStore.getInstance().addUser(newUser);

            Stage stage = (Stage) customerIdField.getScene().getWindow();
            stage.close();
        } else {
            errorLabel.setText("Missing or invalid input");
        }
    }

    /**
     * Validate the input fields before adding a new user.
     *
     * @return true if all input is valid, false otherwise.
     */
    private boolean validateInput() {
        return FormValidator.isNotEmpty(customerIdField.getText()) && FormValidator.isNotEmpty(fullNameField.getText()) && FormValidator.isNotEmpty(addressField.getText()) && FormValidator.isValidPhoneNumber(contactNumberField.getText()) && FormValidator.isValidEmail(emailField.getText()) && FormValidator.isNumeric(meterNumberField.getText()) && FormValidator.hasMinLength(passwordField.getText(), 6);
    }
}
