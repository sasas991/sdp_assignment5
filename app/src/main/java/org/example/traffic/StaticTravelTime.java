package org.example.traffic;

import java.util.Map;

public class StaticTravelTime implements TravelTimeProvider {
    private final Map<String, Integer> minutesByRoute;

    public StaticTravelTime(Map<String, Integer> minutesByRoute) {
        this.minutesByRoute = Map.copyOf(minutesByRoute);
    }

    @Override
    public int minutesTo(String from, String to) {
        return minutesByRoute.getOrDefault(from + ":" + to, 60);
    }
}
