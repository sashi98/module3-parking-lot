package com.he.trainer.bootcamp;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public boolean park(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }

    public boolean unPark(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }
}
