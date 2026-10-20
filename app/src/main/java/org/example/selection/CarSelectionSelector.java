package org.example.selection;

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
        return new CarSelectionContext(selectStrategy(request)).select(cars, request);
    }

    public CarSelectionStrategy selectStrategy(RentalRequest request) {
        if (request.hoursUntilPickup() <= 2) {
            return new FastestPickupCar(travelTimeProvider);
        } else if (request.budgetPriority()) {
            return new CheapestCar();
        } else {
            return new HighestRatedCar();
        }
    }
}
