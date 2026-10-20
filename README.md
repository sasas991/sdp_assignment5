# Car Rental System - Strategy Pattern
Aldiyar Kairollin
| SE-2539

This project compares rental pricing algorithms, then selects a car for a customer request.

Run the demo with `./gradlew :app:run`. Run the tests with `./gradlew :app:test`.

## Classes

| Class | Responsibility |
| --- | --- |
| `App` | Demonstrates pricing, manual car selection, automatic car selection, runtime strategy replacement. |
| `Car` | Stores vehicle name, daily rate, rating, pickup location. |
| `RentalRequest` | Stores rental length, estimated distance, pickup details, budget preference. |
| `PricingStrategy` | Defines the rental price calculation contract. |
| `DailyPricing` | Calculates rental cost from the number of days. |
| `MileagePricing` | Calculates rental cost from estimated distance. |
| `WeeklyPackagePricing` | Calculates rental cost from weekly packages plus remaining days. |
| `PricingContext` | Delegates price calculation to its replaceable strategy. |
| `CarSelectionStrategy` | Defines the car selection contract. |
| `CheapestCar` | Selects the car with the lowest daily rate. |
| `FastestPickupCar` | Selects the car with the shortest pickup time. |
| `HighestRatedCar` | Selects the car with the highest rating. |
| `CarSelectionContext` | Delegates car selection to its replaceable strategy. |
| `CarSelectionSelector` | Chooses a strategy from request urgency, budget preference. |
| `TravelTimeProvider` | Defines access to estimated travel times. |
| `StaticTravelTime` | Supplies simulated travel times from fixed route data. |
| `AppTest` | Verifies the console demonstration output. |
| `PricingTest` | Verifies each pricing algorithm plus a shared nonnegative price property. |
| `CarSelectionTest` | Verifies each car selection algorithm plus runtime strategy replacement. |
| `CarSelectionSelectorTest` | Verifies every automatic selection rule. |

## Why Strategy

Pricing and car selection are two families of interchangeable algorithms. Each family has a small interface, separate implementations, and a context that delegates work to its current implementation. The application can replace an implementation while it runs. For example, the same rental request can be priced by days, distance, or weekly packages without changing `PricingContext`. Adding another pricing algorithm means adding another implementation of `PricingStrategy`; the context does not need a new branch. Car selection follows the same structure. `CarSelectionSelector` decides which selection strategy fits the current request, while `CarSelectionContext` only runs the chosen algorithm.

This is not State: no strategy represents a stage in the rental lifecycle, and no strategy triggers a transition to another state. The caller selects an algorithm based on the current situation. This is not Template Method: there is no base class that fixes an algorithm skeleton for subclasses to fill in. Each strategy implements its complete algorithm independently. The travel time source sits behind `TravelTimeProvider`, so the pickup strategy does not depend on a particular source of data. Tests replace that source with fixed values.

## Refactoring history

- [Before: selection algorithms in one class](https://github.com/sasas991/sdp_assignment5/commit/8e80c18bec8db9bdf65325ec450f1193a268a331)
- [After: selection algorithms as strategies](https://github.com/sasas991/sdp_assignment5/commit/a76a94c26fb09419c93268e8a53ff1fb324f893a)

The same `CarSelectionSelectorTest` passes on both commits.
