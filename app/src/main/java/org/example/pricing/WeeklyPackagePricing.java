package org.example.pricing;

import org.example.model.Car;
import org.example.model.RentalRequest;

public class WeeklyPackagePricing implements PricingStrategy {
    @Override
    public double calculate(Car car, RentalRequest request) {
        int fullWeeks = request.days() / 7;
        int remainingDays = request.days() % 7;
        return (fullWeeks * 5 + remainingDays) * car.dailyRate();
    }
}
