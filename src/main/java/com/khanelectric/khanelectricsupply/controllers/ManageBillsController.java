package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.constants.Routes;
import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.models.Bill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;

public class ManageBillsController {

    @FXML
    private TableView<Bill> billsTable; // Changed from usersTable to billsTable
    @FXML
    private TableColumn<Bill, String> billIdColumn; // New column for Bill ID
    @FXML
    private TableColumn<Bill, String> customerIdColumn;
    @FXML
    private TableColumn<Bill, String> nameColumn;

    @FXML
    private TableColumn<Bill, String> meterNumberColumn;

    @FXML
    private TableColumn<Bill, String> addressColumn;

    @FXML
    private TableColumn<Bill, String> amountColumn;

    @FXML
    private TableColumn<Bill, String> statusColumn;

    @FXML
    private TableColumn<Bill, String> actionsColumn;

    @FXML
    private Button addButton;

    @FXML
    private Text topText;

    private boolean isAdmin = DataStore.getInstance().currentUser.isAdmin();


    public void initialize() {

        loadBills();

        setColumnWidths();
        setColumnAlignment(billIdColumn, Pos.CENTER_LEFT);
        setColumnAlignment(customerIdColumn, Pos.CENTER_LEFT);
        setColumnAlignment(nameColumn, Pos.CENTER_LEFT);
        setColumnAlignment(meterNumberColumn, Pos.CENTER_LEFT);
        setColumnAlignment(addressColumn, Pos.CENTER_LEFT);
        setColumnAlignment(amountColumn, Pos.CENTER_LEFT);
        setColumnAlignment(statusColumn, Pos.CENTER_LEFT);

        if (!isAdmin) {
            // remove generate new bill
            addButton.setVisible(false);
            topText.setText("Your Bills");
        } else {
            topText.setText("Manage Consumer Bills");
        }
    }

    private void setColumnWidths() {
        double widthPercentage = 0.125; // 20% for each column except actions
        billIdColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
        customerIdColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
        nameColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
        meterNumberColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
        addressColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
        amountColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
        statusColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
        actionsColumn.prefWidthProperty().bind(billsTable.widthProperty().multiply(widthPercentage));
    }

    private <T> void setColumnAlignment(TableColumn<Bill, T> column, Pos alignment) {
        column.setCellFactory(col -> new TableCell<Bill, T>() {
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


    @FXML
    private void handleAddBill() {
        try {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Add Bill");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.ADD_BILL_DIALOG_VIEW_PATH));
            dialog.getDialogPane().setContent(loader.load());

            dialog.getDialogPane().getButtonTypes().clear();
            dialog.showAndWait();
            loadBills();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    private void loadBills() {
        billIdColumn.setCellValueFactory(new PropertyValueFactory<>("billId"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        meterNumberColumn.setCellValueFactory(new PropertyValueFactory<>("meterNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountPayable"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewButton = new Button("View");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewButton);
                    viewButton.setOnAction(event -> {
                        Bill bill = getTableView().getItems().get(getIndex());
                        viewBill(bill);
                    });
                }
            }
        });

        billsTable.getItems().setAll(DataStore.getInstance().getBills());
    }


    private void viewBill(Bill bill) {
        try {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Bill Details");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.BILL_VIEW_PATH));
            dialog.getDialogPane().setContent(loader.load());

            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}
