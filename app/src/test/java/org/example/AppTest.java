package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void demoShowsPricingAndSelection() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            App.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String result = output.toString(StandardCharsets.UTF_8);
        assertTrue(result.contains("DailyPricing: 80000.0"));
        assertTrue(result.contains("MileagePricing: 40000.0"));
        assertTrue(result.contains("WeeklyPackagePricing: 60000.0"));
        assertTrue(result.contains("Manual: Budget"));
        assertTrue(result.contains("Automatic: Nearby"));
        assertTrue(result.contains("Switched: Premium"));
    }
}
