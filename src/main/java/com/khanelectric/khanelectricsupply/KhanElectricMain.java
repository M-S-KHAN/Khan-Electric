package com.khanelectric.khanelectricsupply;

import com.khanelectric.khanelectricsupply.constants.Routes;
import com.khanelectric.khanelectricsupply.data_store.DataStore;
import com.khanelectric.khanelectricsupply.utility.RoutingHelper;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class that starts the Khan Electric Supply application.
 */
public class KhanElectricMain extends Application {

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the application and navigates to the login view.
     *
     * @param stage The primary JavaFX stage where scenes will be displayed.
     * @throws Exception If an error occurs during initialization.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize the DataStore
        DataStore.getInstance();

        // Create a RoutingHelper instance associated with the primary stage
        RoutingHelper.getInstance(stage).navigateTo(Routes.LOGIN_VIEW_PATH);
    }
}
