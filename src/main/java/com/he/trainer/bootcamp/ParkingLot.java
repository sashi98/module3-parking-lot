package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import com.he.trainer.bootcamp.exception.VehicleNotFoundException;
import com.he.trainer.bootcamp.observers.ParkingLotObserver;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLot {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final int capacity;
    private final List<ParkingLotObserver> observers;
    private int cost;

    private ParkingLot(int capacity, ParkingLotObserver... observers) {
        this.capacity = capacity;
        this.observers = Arrays.asList(observers);
    }

    private ParkingLot(int capacity, int cost, ParkingLotObserver[] observers) {
        this(capacity, observers);
        this.cost = cost;
    }

    public boolean park(Vehicle vehicle) throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        if (isFull()) {
            throw new ParkingBeyondCapacityException("No space to park vehicle.");
        }
        if (isParked(vehicle)) {
            throw new VehicleAlreadyParkedException("Vehicle is already parked.");
        }
        vehicles.add(vehicle);
        notifyParkingIsFull();
        return true;
    }

    public boolean unPark(Vehicle vehicle) throws VehicleNotFoundException {
        if (!isParked(vehicle))
            throw new VehicleNotFoundException("No Vehicle found to un-park.");

        vehicles.remove(vehicle);
        notifyParkingIsAvailable();
        return true;
    }

    private void notifyParkingIsFull() {
        if (isFull()) {
            observers.forEach(ParkingLotObserver::parkingIsFullNotification);
        }
    }

    public boolean isParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    private void notifyParkingIsAvailable() {
        if (CollectionUtils.isNotEmpty(observers)) {
            observers.forEach(ParkingLotObserver::parkingIsAvailableNotification);
        }
    }

    public boolean isFull() {
        return vehicles.size() == capacity;
    }

    public int capacity() {
        return capacity;
    }

    public int cost() {
       return cost;
    }

    public int vehicleCount() {
        return vehicles.size();
    }

    public static ParkingLot parkingLot(int capacity) {
        return new ParkingLot(capacity);
    }

    public static ParkingLot parkingLot(int capacity, ParkingLotObserver... observers) {
        return new ParkingLot(capacity, observers);
    }

    public static ParkingLot parkingLot(int capacity, int cost, ParkingLotObserver... observers) {
        return new ParkingLot(capacity, cost, observers);
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "vehicles=" + vehicles +
                ", capacity=" + capacity +
                ", vehicleCount=" + vehicleCount() +
                ", cost=" + cost +
                ", observers=" + observers +
                '}';
    }
}
