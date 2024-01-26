package com.khanelectric.khanelectricsupply.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Helper class for managing navigation between JavaFX scenes using FXML files.
 */
public class RoutingHelper {

    private final Stage stage;
    private static RoutingHelper instance;

    /**
     * Constructs a RoutingHelper instance with the specified stage.
     *
     * @param stage The JavaFX stage where scenes will be displayed.
     */
    public RoutingHelper(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets or creates a RoutingHelper instance associated with the specified stage.
     *
     * @param stage The JavaFX stage where scenes will be displayed.
     * @return A RoutingHelper instance.
     */
    public static RoutingHelper getInstance(Stage stage) {
        if (instance == null) {
            instance = new RoutingHelper(stage);
        }
        return instance;
    }

    /**
     * Navigates to a new scene defined by the given FXML file path.
     *
     * @param fxmlPath The path to the FXML file representing the new scene.
     */
    public void navigateTo(String fxmlPath) {
        try {
            // Ensure the FXML path starts with a slash indicating it's from the root of the classpath
            if (!fxmlPath.startsWith("/")) {
                fxmlPath = "/" + fxmlPath;
            }

            URL fxmlUrl = getClass().getResource(fxmlPath);
            if (fxmlUrl == null) {
                throw new IOException("Cannot load resource: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent rootNode = loader.load();

            Scene scene = new Scene(rootNode);

            stage.setTitle("Khan Electric Supply");

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, possibly with an error dialog
        }
    }
}
