package org.example.selection;

import java.util.List;
import org.example.model.Car;
import org.example.model.RentalRequest;

public class CarSelectionContext {
    private CarSelectionStrategy strategy;

    public CarSelectionContext(CarSelectionStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CarSelectionStrategy strategy) {
        this.strategy = strategy;
    }

    public Car select(List<Car> cars, RentalRequest request) {
        return strategy.select(cars, request);
    }
}
