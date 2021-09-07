package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean park(Vehicle vehicle) throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        if (vehicles.size() == capacity) throw new ParkingBeyondCapacityException("No space to park vehicle.");
        if (vehicles.contains(vehicle)){
            throw new VehicleCouldNotBeParkedException("Vehicle is already parked.");
        }
        return vehicles.add(vehicle);
    }

    public boolean unPark(Vehicle vehicle) throws VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        if (!vehicles.contains(vehicle) && vehicles.size()>0) throw new VehicleCouldNotBeUnParkedException("No Vehicle found to un-park.");
        if (vehicles.size()==0) throw new UnParkingFromEmptyLotException("Vehicle cannot be up-parked from empty lot.");
        return vehicles.remove(vehicle);
    }
}
