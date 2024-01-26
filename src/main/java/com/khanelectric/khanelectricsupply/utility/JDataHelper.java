package com.khanelectric.khanelectricsupply.utility;

import java.io.*;

/**
 * Helper class for saving and loading data to/from files using Java serialization.
 */
public class JDataHelper {

    /**
     * Saves data to a file using Java serialization.
     *
     * @param filename The name of the file to save data to.
     * @param data     The data to be saved.
     * @throws IOException If an I/O error occurs while saving the data.
     */
    public static <T> void saveDataToFile(String filename, T data) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        }
    }

    /**
     * Loads data from a file using Java deserialization.
     *
     * @param filename The name of the file to load data from.
     * @return The loaded data.
     * @throws IOException            If an I/O error occurs while loading the data.
     * @throws ClassNotFoundException If the class of the loaded object cannot be found.
     */
    public static <T> T loadDataFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) in.readObject();
        }
    }
}
