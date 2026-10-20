package org.example.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.example.model.Car;
import org.example.model.RentalRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PricingTest {
    private final Car car = new Car("Basic", 100, 4.0, "A");
    private final RentalRequest request = new RentalRequest(8, 20, "Customer", 24, false);

    @Test
    void dailyPricingUsesRentalDays() {
        assertEquals(800, new DailyPricing().calculate(car, request));
    }

    @Test
    void mileagePricingUsesEstimatedDistance() {
        assertEquals(20, new MileagePricing().calculate(car, request));
    }

    @Test
    void weeklyPackagePricingUsesFullWeeksAndRemainingDays() {
        assertEquals(600, new WeeklyPackagePricing().calculate(car, request));
    }

    @ParameterizedTest
    @MethodSource("strategies")
    void priceIsNeverNegative(PricingStrategy strategy) {
        assertTrue(strategy.calculate(car, request) >= 0);
    }

    private static Stream<PricingStrategy> strategies() {
        return Stream.of(new DailyPricing(), new MileagePricing(), new WeeklyPackagePricing());
    }
}
