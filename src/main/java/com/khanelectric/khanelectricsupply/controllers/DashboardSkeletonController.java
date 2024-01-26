package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.constants.ResourcePaths;
import com.khanelectric.khanelectricsupply.constants.Routes;
import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.utility.Helpers;
import com.khanelectric.khanelectricsupply.utility.RoutingHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Dashboard Skeleton view.
 */
public class DashboardSkeletonController {

    @FXML
    private Button dashboardButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button billsButton;
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
        loadView(Routes.ADMIN_DASHBOARD_VIEW_PATH);
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
     * Handles the dashboard button click event. Loads the admin dashboard view.
     */
    @FXML
    private void handleDashboard() {
        loadView(Routes.ADMIN_DASHBOARD_VIEW_PATH);
    }

    /**
     * Handles the "Manage Users" button click event. Loads the Manage Users view.
     */
    @FXML
    private void handleManageUsers() {
        // Load Manage Users View
        loadView(Routes.MANAGE_USERS_VIEW_PATH);
    }

    /**
     * Handles the "Manage Bills" button click event. Loads the Manage Bills view.
     */
    @FXML
    private void handleManageBills() {
        // Load Manage Bills View
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
