package org.example.model;

public record RentalRequest(
        int days,
        int estimatedKm,
        String pickupLocation,
        int hoursUntilPickup,
        boolean budgetPriority) {
}
