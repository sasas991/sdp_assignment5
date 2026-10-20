package org.example.pricing;

import org.example.model.Car;
import org.example.model.RentalRequest;

public class MileagePricing implements PricingStrategy {
    @Override
    public double calculate(Car car, RentalRequest request) {
        double pricePerKm = car.dailyRate() / 100;
        return request.estimatedKm() * pricePerKm;
    }
}
