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
        if (isParkingFull()) throw new ParkingBeyondCapacityException("No space to park vehicle.");
        if (isParked(vehicle)){
            throw new VehicleCouldNotBeParkedException("Vehicle is already parked.");
        }
        return vehicles.add(vehicle);
    }

    private boolean isParkingFull() {
        return vehicles.size() == capacity;
    }

    public boolean isParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    public boolean unPark(Vehicle vehicle) throws VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        if (!isParked(vehicle) && vehicles.size()>0) throw new VehicleCouldNotBeUnParkedException("No Vehicle found to un-park.");
        if (vehicles.size()==0) throw new UnParkingFromEmptyLotException("Vehicle cannot be up-parked from empty lot.");
        return vehicles.remove(vehicle);
    }


}
