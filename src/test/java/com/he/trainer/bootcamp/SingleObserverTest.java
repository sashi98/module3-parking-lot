package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import com.he.trainer.bootcamp.exception.VehicleNotFoundException;
import com.he.trainer.bootcamp.observers.Owner;
import com.he.trainer.bootcamp.observers.TrafficCop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.ParkingLot.parkingLot;
import static com.he.trainer.bootcamp.observers.Owner.owner;
import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static com.he.trainer.bootcamp.observers.TrafficCop.trafficcop;

public class SingleObserverTest {
    @Test
    public void ownerIsNotifiedIfParkingLotIsFull() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Owner owner = owner();
        ParkingLot parkingLot = parkingLot(1, owner);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        Assertions.assertTrue(owner.isParkingFull());
    }

    @Test
    public void ownerIsNotifiedIfParkingLotIsAvailable() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        Owner owner = owner();
        ParkingLot parkingLot = parkingLot(1, owner);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        Assertions.assertFalse(owner.isParkingFull());
    }

    @Test
    public void trafficCopIsNotifiedIfParkingLotIsFull() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = parkingLot(1, trafficCop);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        Assertions.assertTrue(trafficCop.isParkingFull());
    }

    @Test
    public void trafficCopIsNotifiedIfParkingLotIsAvailable() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = parkingLot(2, trafficCop);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        Assertions.assertFalse(trafficCop.isParkingFull());
    }
}
