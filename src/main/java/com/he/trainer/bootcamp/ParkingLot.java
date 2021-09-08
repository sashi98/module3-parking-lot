package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.UnParkingFromEmptyLotException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import com.he.trainer.bootcamp.observers.ParkingLotObserver;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParkingLot {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final int capacity;
    private final List<ParkingLotObserver> observers;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.observers = Collections.emptyList();
    }

    public ParkingLot(int capacity, ParkingLotObserver... observers) {
        this.capacity = capacity;
        this.observers = Arrays.asList(observers);
    }

    public boolean park(Vehicle vehicle) throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        if (isParkingFull()) {
            throw new ParkingBeyondCapacityException("No space to park vehicle.");
        }
        if (isParked(vehicle)) {
            throw new VehicleCouldNotBeParkedException("Vehicle is already parked.");
        }
        vehicles.add(vehicle);
        notifyParkingIsFull();
        return true;
    }

    private void notifyParkingIsFull() {
        if (CollectionUtils.isNotEmpty(observers) && isParkingFull()) {
            observers.forEach(observer -> observer.parkingIsFullNotification());
        }
    }

    private boolean isParkingFull() {
        return vehicles.size() == capacity;
    }

    public boolean isParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    public boolean unPark(Vehicle vehicle) throws VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        if (!isParked(vehicle) && isParkingAvailable())
            throw new VehicleCouldNotBeUnParkedException("No Vehicle found to un-park.");
        if (isParkingLotEmpty())
            throw new UnParkingFromEmptyLotException("Vehicle cannot be up-parked from empty lot.");
        vehicles.remove(vehicle);
        notifyParkingIsAvailable();
        return true;
    }

    private void notifyParkingIsAvailable() {
        if (CollectionUtils.isNotEmpty(observers)) {
            observers.forEach(observer -> observer.parkingIsAvailableNotification());
        }
    }

    private boolean isParkingAvailable() {
        return vehicles.size() > 0 && vehicles.size() <= capacity;
    }

    private boolean isParkingLotEmpty() {
        return vehicles.size() == 0;
    }


}
