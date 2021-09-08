package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.UnParkingFromEmptyLotException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import com.he.trainer.bootcamp.observers.Owner;
import com.he.trainer.bootcamp.observers.TrafficCop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.observers.Owner.owner;
import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static com.he.trainer.bootcamp.observers.TrafficCop.trafficcop;

public class SingleObserverTest {
    @Test
    public void ownerIsNotifiedIfParkingLotIsFull() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        Owner owner = owner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        Assertions.assertTrue(owner.isParkingFull());
    }

    @Test
    public void ownerIsNotifiedIfParkingLotIsAvailable() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        Owner owner = owner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        Assertions.assertFalse(owner.isParkingFull());
    }

    @Test
    public void trafficCopIsNotifiedIfParkingLotIsFull() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = new ParkingLot(1, trafficCop);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        Assertions.assertTrue(trafficCop.isParkingFull());
    }

    @Test
    public void trafficCopIsNotifiedIfParkingLotIsAvailable() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = new ParkingLot(2, trafficCop);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        Assertions.assertFalse(trafficCop.isParkingFull());
    }
}
