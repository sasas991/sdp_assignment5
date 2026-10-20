package org.example.selection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.example.model.Car;
import org.example.model.RentalRequest;
import org.example.traffic.TravelTimeProvider;
import org.junit.jupiter.api.Test;

class CarSelectionSelectorTest {
    private final List<Car> cars = List.of(
            new Car("Budget", 10000, 3.5, "A"),
            new Car("Nearby", 15000, 4.0, "B"),
            new Car("Premium", 20000, 5.0, "C"));

    private final TravelTimeProvider travelTimeProvider = (from, to) -> switch (to) {
        case "A" -> 30;
        case "B" -> 5;
        default -> 15;
    };

    private final CarSelectionSelector selector = new CarSelectionSelector(travelTimeProvider);

    @Test
    void urgentRequestSelectsFastestPickup() {
        RentalRequest request = new RentalRequest(8, 400, "Customer", 1, true);
        assertEquals("Nearby", selector.select(cars, request).name());
    }

    @Test
    void budgetRequestSelectsCheapestCar() {
        RentalRequest request = new RentalRequest(8, 400, "Customer", 24, true);
        assertEquals("Budget", selector.select(cars, request).name());
    }

    @Test
    void regularRequestSelectsHighestRatedCar() {
        RentalRequest request = new RentalRequest(8, 400, "Customer", 24, false);
        assertEquals("Premium", selector.select(cars, request).name());
    }
}
