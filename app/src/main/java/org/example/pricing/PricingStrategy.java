package org.example.pricing;

import org.example.model.Car;
import org.example.model.RentalRequest;

public interface PricingStrategy {
    double calculate(Car car, RentalRequest request);
}
