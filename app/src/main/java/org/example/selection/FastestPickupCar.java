package org.example.selection;

import java.util.Comparator;
import java.util.List;
import org.example.model.Car;
import org.example.model.RentalRequest;
import org.example.traffic.TravelTimeProvider;

public class FastestPickupCar implements CarSelectionStrategy {
    private final TravelTimeProvider travelTimeProvider;

    public FastestPickupCar(TravelTimeProvider travelTimeProvider) {
        this.travelTimeProvider = travelTimeProvider;
    }

    @Override
    public Car select(List<Car> cars, RentalRequest request) {
        return cars.stream()
                .min(Comparator.comparingInt(car -> travelTimeProvider.minutesTo(
                        request.pickupLocation(), car.pickupLocation())))
                .orElseThrow();
    }
}
