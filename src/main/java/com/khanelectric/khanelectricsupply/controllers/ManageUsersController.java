package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.constants.Routes;
import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Controller class for managing users.
 */
public class ManageUsersController {

    @FXML
    private Button addButton; // Button to add a new user

    @FXML
    private TableView<User> usersTable; // Table to display users
    @FXML
    private TableColumn<User, String> customerIdColumn; // Customer ID column
    @FXML
    private TableColumn<User, String> nameColumn; // Full name column
    @FXML
    private TableColumn<User, String> meterNumberColumn; // Meter number column
    @FXML
    private TableColumn<User, String> addressColumn; // Address column
    @FXML
    private TableColumn<User, String> actionsColumn; // Actions column (Delete button)

    /**
     * Initializes the controller.
     */
    public void initialize() {
        loadUsers(); // Load users into the table
        setColumnWidths(); // Set column widths
        setColumnAlignment(customerIdColumn, Pos.CENTER_LEFT); // Set alignment for specific columns
        setColumnAlignment(nameColumn, Pos.CENTER_LEFT);
        setColumnAlignment(meterNumberColumn, Pos.CENTER_LEFT);
        setColumnAlignment(addressColumn, Pos.CENTER_LEFT);
    }

    /**
     * Set column widths as a percentage of the table's width.
     */
    private void setColumnWidths() {
        double widthPercentage = 0.20; // 20% for each column except actions
        customerIdColumn.prefWidthProperty().bind(usersTable.widthProperty().multiply(widthPercentage));
        nameColumn.prefWidthProperty().bind(usersTable.widthProperty().multiply(widthPercentage));
        meterNumberColumn.prefWidthProperty().bind(usersTable.widthProperty().multiply(widthPercentage));
        addressColumn.prefWidthProperty().bind(usersTable.widthProperty().multiply(widthPercentage));
        actionsColumn.prefWidthProperty().bind(usersTable.widthProperty().multiply(0.20)); // Fixed 20% for the actions column
    }

    /**
     * Set column alignment for custom alignment.
     *
     * @param column    The TableColumn to set alignment for.
     * @param alignment The alignment to set.
     * @param <T>       The type of the column.
     */
    private <T> void setColumnAlignment(TableColumn<User, T> column, Pos alignment) {
        column.setCellFactory(col -> new TableCell<User, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setAlignment(alignment);
                }
            }
        });
    }

    /**
     * Handle the "Add User" button click.
     */
    @FXML
    private void handleAddUser() {
        try {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Add User");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.ADD_USER_DIALOG_VIEW_PATH));
            dialog.getDialogPane().setContent(loader.load());

            dialog.getDialogPane().getButtonTypes().clear();
            dialog.showAndWait();
            loadUsers(); // Reload the users table after adding a new user
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    /**
     * Load users into the table.
     */
    private void loadUsers() {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        meterNumberColumn.setCellValueFactory(new PropertyValueFactory<>("meterNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Create a custom cell for the "Actions" column (Delete button)
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(event -> {
                        User user = getTableView().getItems().get(getIndex());
                        deleteUser(user); // Handle the "Delete" button click
                    });
                }
            }
        });

        usersTable.getItems().setAll(DataStore.getInstance().getUsers()); // Load users from the data store
    }

    /**
     * Handle the "Delete" button click to delete a user.
     *
     * @param user The user to delete.
     */
    private void deleteUser(User user) {
        // Implement user deletion logic
        DataStore.getInstance().deleteUser(user);
        loadUsers(); // Reload the users table after deleting a user
    }
}
