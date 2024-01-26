package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.models.Bill;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller class for the user dashboard.
 */
public class UserDashboardController {

    @FXML
    private BarChart<String, Number> monthlyBillChart; // Bar chart to display monthly bills
    @FXML
    private BarChart<String, Number> monthlyUnitsChart; // Bar chart to display monthly units consumed

    /**
     * Initialize the controller.
     */
    @FXML
    public void initialize() {
        loadMonthlyBillData(); // Load and display monthly bill data
        loadMonthlyUnitsData(); // Load and display monthly units consumed data
    }

    /**
     * Load and display monthly bill data in the chart.
     */
    private void loadMonthlyBillData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Bill"); // Set the series name

        // Assume DataStore provides a method to get bills for the logged-in user
        List<Bill> bills = DataStore.getInstance().getBills();

        // Group bills by month and calculate the total bill amount for each month
        Map<String, Double> billAmountsByMonth = bills.stream().collect(Collectors.groupingBy(bill -> String.format("%s %d", bill.getEndDate().getMonth(), bill.getEndDate().getYear()), Collectors.summingDouble(Bill::getAmountPayable)));

        // Add data points to the chart series
        billAmountsByMonth.forEach((month, sum) -> series.getData().add(new XYChart.Data<>(month, sum)));

        // Add the series to the chart
        monthlyBillChart.getData().add(series);
    }

    /**
     * Load and display monthly units consumed data in the chart.
     */
    private void loadMonthlyUnitsData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Units Consumed"); // Set the series name

        // Assume DataStore provides a method to get bills for the logged-in user
        List<Bill> bills = DataStore.getInstance().getBills();

        // Group bills by month and calculate the total units consumed for each month
        Map<String, Double> unitsByMonth = bills.stream().collect(Collectors.groupingBy(bill -> String.format("%s %d", bill.getEndDate().getMonth(), bill.getEndDate().getYear()), Collectors.summingDouble(Bill::getUnitsConsumed)));

        // Add data points to the chart series
        unitsByMonth.forEach((month, sum) -> series.getData().add(new XYChart.Data<>(month, sum)));

        // Add the series to the chart
        monthlyUnitsChart.getData().add(series);
    }
}
