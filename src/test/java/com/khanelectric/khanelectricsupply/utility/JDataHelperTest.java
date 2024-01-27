package com.khanelectric.khanelectricsupply.utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class JDataHelperTest {
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file for testing
        tempFile = Files.createTempFile("test", "jdata");
    }

    @AfterEach
    void tearDown() {
        // Delete the temporary file after each test
        tempFile.toFile().delete();
    }

    @Test
    void saveDataToFile_AndLoadDataFromFile_ShouldWorkCorrectly() throws IOException, ClassNotFoundException {
        String testData = "This is a test string.";

        // Save the test data to the temporary file
        JDataHelper.saveDataToFile(tempFile.toString(), testData);

        // Load the data back from the file
        String loadedData = JDataHelper.loadDataFromFile(tempFile.toString());

        // Verify that the saved and loaded data are the same
        assertEquals(testData, loadedData);
    }

    @Test
    void saveDataToFile_ShouldThrowIOExceptionForInvalidPath() {
        String invalidPath = "/invalid/path/test.jdata";
        assertThrows(IOException.class, () -> JDataHelper.saveDataToFile(invalidPath, "test data"));
    }

    @Test
    void loadDataFromFile_ShouldThrowIOExceptionForInvalidPath() {
        String invalidPath = "/invalid/path/test.jdata";
        assertThrows(IOException.class, () -> JDataHelper.loadDataFromFile(invalidPath));
    }

    @Test
    void loadDataFromFile_ShouldThrowClassNotFoundExceptionForInvalidData() throws IOException {
        // Save some non-serializable data to the file
        Files.writeString(tempFile, "Non-serializable data");

        // Attempting to load this data as a serialized object should throw an exception
        assertThrows(StreamCorruptedException.class, () -> JDataHelper.loadDataFromFile(tempFile.toString()));
    }
}
