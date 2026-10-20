package org.example.selection;

import java.util.List;
import org.example.model.Car;
import org.example.model.RentalRequest;

public interface CarSelectionStrategy {
    Car select(List<Car> cars, RentalRequest request);
}
