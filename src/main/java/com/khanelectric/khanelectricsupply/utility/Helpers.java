package com.khanelectric.khanelectricsupply.utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Random;

/**
 * Helper methods for various tasks in the application.
 */
public class Helpers {

    /**
     * Loads an image from the specified path and sets it in an ImageView with the given width.
     *
     * @param inst  An instance of the calling class (typically 'this' from the controller).
     * @param view  The ImageView to set the loaded image.
     * @param path  The path to the image resource.
     * @param width The desired width of the loaded image.
     */
    public static void loadImage(Object inst, ImageView view, String path, double width) {
        URL imageUrl = inst.getClass().getResource(path);
        if (imageUrl != null) {
            Image logoImage = new Image(imageUrl.toExternalForm());
            view.setImage(logoImage);
            view.setFitWidth(width);
            view.setPreserveRatio(true);
        } else {
            // Handle the situation where the image is not found
            System.out.println("Image not found");
        }
    }

    /**
     * Generates a random 10-digit bill ID.
     *
     * @return A randomly generated 10-digit bill ID.
     */
    public static String generateBillId() {
        // Generate a random 10-digit bill ID
        Random random = new Random();
        StringBuilder billId = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            billId.append(digit);
        }

        return billId.toString();
    }
}
