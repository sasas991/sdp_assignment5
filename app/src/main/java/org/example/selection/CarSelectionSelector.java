package org.example.selection;

import java.util.Comparator;
import java.util.List;
import org.example.model.Car;
import org.example.model.RentalRequest;
import org.example.traffic.TravelTimeProvider;

public class CarSelectionSelector {
    private final TravelTimeProvider travelTimeProvider;

    public CarSelectionSelector(TravelTimeProvider travelTimeProvider) {
        this.travelTimeProvider = travelTimeProvider;
    }

    public Car select(List<Car> cars, RentalRequest request) {
        if (request.hoursUntilPickup() <= 2) {
            return cars.stream()
                    .min(Comparator.comparingInt(car -> travelTimeProvider.minutesTo(
                            request.pickupLocation(), car.pickupLocation())))
                    .orElseThrow();
        } else if (request.budgetPriority()) {
            return cars.stream().min(Comparator.comparingDouble(Car::dailyRate)).orElseThrow();
        } else {
            return cars.stream().max(Comparator.comparingDouble(Car::rating)).orElseThrow();
        }
    }
}
