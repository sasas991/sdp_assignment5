package org.example.selection;

import java.util.Comparator;
import java.util.List;
import org.example.model.Car;
import org.example.model.RentalRequest;

public class CheapestCar implements CarSelectionStrategy {
    @Override
    public Car select(List<Car> cars, RentalRequest request) {
        return cars.stream().min(Comparator.comparingDouble(Car::dailyRate)).orElseThrow();
    }
}
