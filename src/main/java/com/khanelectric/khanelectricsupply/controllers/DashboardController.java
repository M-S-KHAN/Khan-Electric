package com.khanelectric.khanelectricsupply.controllers;

import com.khanelectric.khanelectricsupply.data_store.DataStore;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

/**
 * Controller class for the Dashboard view.
 */
public class DashboardController {

    @FXML
    private BarChart<String, Number> monthlySalesChart;
    @FXML
    private BarChart<String, Number> totalUnitsChart;
    @FXML
    private PieChart paidUnpaidChart;

    /**
     * Initializes the controller. Sets up the charts with relevant data.
     */
    @FXML
    public void initialize() {
        setupMonthlySalesChart();
        setupTotalUnitsChart();
        setupPaidUnpaidChart();
    }

    /**
     * Sets up the Monthly Sales chart with data from the data store.
     */
    private void setupMonthlySalesChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Sales");

        DataStore dataStore = DataStore.getInstance();
        Map<String, Double> salesData = dataStore.getMonthlySalesData();
        for (Map.Entry<String, Double> entry : salesData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        monthlySalesChart.getData().add(series);
    }

    /**
     * Sets up the Total Units chart with data from the data store.
     */
    private void setupTotalUnitsChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Units");

        DataStore dataStore = DataStore.getInstance();
        Map<String, Double> unitsData = dataStore.getTotalUnitsData();
        for (Map.Entry<String, Double> entry : unitsData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        totalUnitsChart.getData().add(series);
    }

    /**
     * Sets up the Paid/Unpaid Bills chart with data from the data store.
     */
    private void setupPaidUnpaidChart() {
        // Populate paid/unpaid bills chart
        DataStore dataStore = DataStore.getInstance();
        Map<String, Integer> paidUnpaidData = dataStore.getPaidUnpaidData();
        for (Map.Entry<String, Integer> entry : paidUnpaidData.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            paidUnpaidChart.getData().add(data);
        }
    }
}
