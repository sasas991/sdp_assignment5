package org.example.selection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.example.model.Car;
import org.example.model.RentalRequest;
import org.example.traffic.TravelTimeProvider;
import org.junit.jupiter.api.Test;

class CarSelectionTest {
    private final List<Car> cars = List.of(
            new Car("Budget", 10000, 3.5, "A"),
            new Car("Nearby", 15000, 4.0, "B"),
            new Car("Premium", 20000, 5.0, "C"));

    private final RentalRequest request = new RentalRequest(8, 400, "Customer", 24, false);

    private final TravelTimeProvider travelTimeProvider = (from, to) -> switch (to) {
        case "A" -> 30;
        case "B" -> 5;
        default -> 15;
    };

    @Test
    void cheapestCarUsesDailyRate() {
        assertEquals("Budget", new CheapestCar().select(cars, request).name());
    }

    @Test
    void fastestPickupCarUsesTravelTime() {
        assertEquals("Nearby", new FastestPickupCar(travelTimeProvider).select(cars, request).name());
    }

    @Test
    void highestRatedCarUsesRating() {
        assertEquals("Premium", new HighestRatedCar().select(cars, request).name());
    }

    @Test
    void contextCanSwitchStrategy() {
        CarSelectionContext context = new CarSelectionContext(new CheapestCar());
        assertEquals("Budget", context.select(cars, request).name());
        context.setStrategy(new HighestRatedCar());
        assertEquals("Premium", context.select(cars, request).name());
    }
}
