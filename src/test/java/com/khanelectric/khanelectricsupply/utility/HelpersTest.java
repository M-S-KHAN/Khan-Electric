package com.khanelectric.khanelectricsupply.utility;

import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {

    @Test
    void generateBillId_ShouldReturnTenDigitString() {
        String billId = Helpers.generateBillId();
        assertEquals(10, billId.length());
    }

    @Test
    void generateBillId_ShouldReturnNumericString() {
        String billId = Helpers.generateBillId();
        assertTrue(billId.matches("\\d{10}"));
    }

    @Test
    void generateBillId_ShouldReturnUniqueValues() {
        String billId1 = Helpers.generateBillId();
        String billId2 = Helpers.generateBillId();
        assertNotEquals(billId1, billId2);
    }
}
