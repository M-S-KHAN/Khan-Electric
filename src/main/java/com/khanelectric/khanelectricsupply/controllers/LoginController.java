package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.constants.ResourcePaths;
import com.khanelectric.khanelectricsupply.constants.Routes;
import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.utility.Helpers;
import com.khanelectric.khanelectricsupply.utility.RoutingHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Controller class for the Login view.
 */
public class LoginController {

    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField userIdField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller. Loads the main logo image.
     */
    @FXML
    private void initialize() {
        Helpers.loadImage(this, logoImageView, ResourcePaths.MAIN_LOGO_PATH, 300.0);
    }

    /**
     * Handles the login button click event.
     */
    @FXML
    private void handleLogin() {
        String userId = userIdField.getText();
        String password = passwordField.getText();

        // Attempt to log in
        if (DataStore.getInstance().login(userId, password) == null) {
            errorLabel.setText("Invalid user ID or password");
            System.out.println("Login failed");
        } else {
            errorLabel.setText("");
            // Load the appropriate dashboard view based on user role (admin or customer)
            if (DataStore.getInstance().currentUser.isAdmin()) {
                RoutingHelper.getInstance((Stage) errorLabel.getScene().getWindow()).navigateTo(Routes.ADMIN_DASHBOARD_SKELETON_VIEW_PATH);
            } else {
                RoutingHelper.getInstance((Stage) errorLabel.getScene().getWindow()).navigateTo(Routes.CUSTOMER_DASHBOARD_VIEW_SKELETON_PATH);
            }
        }
    }
}
