package org.example.pricing;

import org.example.model.Car;
import org.example.model.RentalRequest;

public class DailyPricing implements PricingStrategy {
    @Override
    public double calculate(Car car, RentalRequest request) {
        return car.dailyRate() * request.days();
    }
}
