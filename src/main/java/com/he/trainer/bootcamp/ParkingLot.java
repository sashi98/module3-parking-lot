package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final int capacity;
    private final Owner owner;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.owner = null;
    }

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.owner = owner;
    }

    public boolean park(Vehicle vehicle) throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        if (isParkingFull()){
            throw new ParkingBeyondCapacityException("No space to park vehicle.");
        }
        if (isParked(vehicle)){
            throw new VehicleCouldNotBeParkedException("Vehicle is already parked.");
        }
        vehicles.add(vehicle);
        notifyParkingIsFull();
        return true;
    }

    private void notifyParkingIsFull() {
        if (owner != null && isParkingFull()) {
            owner.parkingIsFullNotification();
        }
    }

    private boolean isParkingFull() {
        return vehicles.size() == capacity;
    }

    public boolean isParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    public boolean unPark(Vehicle vehicle) throws VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        if (!isParked(vehicle) && isParkingAvailable()) throw new VehicleCouldNotBeUnParkedException("No Vehicle found to un-park.");
        if (isParkingLotEmpty()) throw new UnParkingFromEmptyLotException("Vehicle cannot be up-parked from empty lot.");
        vehicles.remove(vehicle);
        notifyParkingIsAvailable();
        return true;
    }

    private void notifyParkingIsAvailable() {
        if (owner != null) {
            owner.parkingIsAvailableNotification();
        }
    }

    private boolean isParkingAvailable() {
        return vehicles.size() <= capacity;
    }

    private boolean isParkingLotEmpty() {
        return vehicles.size()==0;
    }


}
