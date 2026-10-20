package org.example;

import java.util.List;
import java.util.Map;
import org.example.model.Car;
import org.example.model.RentalRequest;
import org.example.pricing.DailyPricing;
import org.example.pricing.MileagePricing;
import org.example.pricing.PricingContext;
import org.example.pricing.PricingStrategy;
import org.example.pricing.WeeklyPackagePricing;
import org.example.selection.CarSelectionContext;
import org.example.selection.CarSelectionSelector;
import org.example.selection.CheapestCar;
import org.example.selection.HighestRatedCar;
import org.example.traffic.StaticTravelTime;

public class App {
    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car("Budget", 10000, 3.5, "A"),
                new Car("Nearby", 15000, 4.0, "B"),
                new Car("Premium", 20000, 5.0, "C"));
        RentalRequest request = new RentalRequest(8, 400, "Customer", 1, true);

        PricingContext pricing = new PricingContext(new DailyPricing());
        for (PricingStrategy strategy : List.of(
                new DailyPricing(), new MileagePricing(), new WeeklyPackagePricing())) {
            pricing.setStrategy(strategy);
            System.out.println(strategy.getClass().getSimpleName() + ": "
                    + pricing.calculate(cars.get(0), request));
        }

        CarSelectionContext selection = new CarSelectionContext(new CheapestCar());
        System.out.println("Manual: " + selection.select(cars, request).name());

        StaticTravelTime travelTime = new StaticTravelTime(Map.of(
                "Customer:A", 30,
                "Customer:B", 5,
                "Customer:C", 15));
        CarSelectionSelector selector = new CarSelectionSelector(travelTime);
        System.out.println("Automatic: " + selector.select(cars, request).name());

        selection.setStrategy(new HighestRatedCar());
        System.out.println("Switched: " + selection.select(cars, request).name());
    }
}
