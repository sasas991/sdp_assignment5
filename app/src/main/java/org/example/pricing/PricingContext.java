package org.example.pricing;

import org.example.model.Car;
import org.example.model.RentalRequest;

public class PricingContext {
    private PricingStrategy strategy;

    public PricingContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(Car car, RentalRequest request) {
        return strategy.calculate(car, request);
    }
}
