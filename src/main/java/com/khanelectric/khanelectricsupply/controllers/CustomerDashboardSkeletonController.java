package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.constants.ResourcePaths;
import com.khanelectric.khanelectricsupply.constants.Routes;
import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.utility.Helpers;
import com.khanelectric.khanelectricsupply.utility.RoutingHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Customer Dashboard Skeleton view.
 */
public class CustomerDashboardSkeletonController {

    @FXML
    private Button dashboardButton;
    @FXML
    private Button currentBillButton;
    @FXML
    private Button billingHistoryButton;
    @FXML
    private Button logoutButton;

    @FXML
    private StackPane contentArea;

    @FXML
    private ImageView logoImageView;

    /**
     * Initializes the controller. Loads the initial dashboard view and sets the logo image.
     */
    public void initialize() {
        // Load the initial dashboard view
        loadView(Routes.CUSTOMER_DASHBOARD_VIEW_PATH);
        Helpers.loadImage(this, logoImageView, ResourcePaths.MAIN_LOGO_PATH, 200.0);
    }

    /**
     * Loads a specified view into the content area.
     *
     * @param fxmlPath The path to the FXML file of the view to be loaded.
     */
    private void loadView(String fxmlPath) {
        try {
            contentArea.getChildren().clear(); // Clear the current view
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().add(view); // Add the new view

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the dashboard button click event. Loads the customer dashboard view.
     */
    @FXML
    private void handleDashboard() {
        loadView(Routes.CUSTOMER_DASHBOARD_VIEW_PATH);
    }

    /**
     * Handles the current bill button click event. Loads the current bill view.
     */
    @FXML
    private void handleCurrentBill() {
        loadView(Routes.CURRENT_BILL_VIEW_PATH);
    }

    /**
     * Handles the billing history button click event. Loads the billing history view.
     */
    @FXML
    private void handleBillingHistory() {
        loadView(Routes.MANAGE_BILLS_VIEW_PATH);
    }

    /**
     * Handles the logout button click event. Navigates to the login view.
     */
    @FXML
    private void handleLogout() {
        DataStore.getInstance().currentUser = null;
        RoutingHelper.getInstance((Stage) logoutButton.getScene().getWindow()).navigateTo(Routes.LOGIN_VIEW_PATH);
    }
}
