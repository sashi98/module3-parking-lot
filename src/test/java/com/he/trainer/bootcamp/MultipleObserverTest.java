package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.UnParkingFromEmptyLotException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import com.he.trainer.bootcamp.observers.Owner;
import com.he.trainer.bootcamp.observers.TrafficCop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static com.he.trainer.bootcamp.observers.Owner.owner;
import static com.he.trainer.bootcamp.observers.TrafficCop.trafficcop;

public class MultipleObserverTest {

    @Test
    public void ownerAndTrafficCopAreNotifiedIfParkingLotIsFull() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        Owner owner = owner();
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = new ParkingLot(1, owner, trafficCop);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        Assertions.assertTrue(owner.isParkingFull());
        Assertions.assertTrue(trafficCop.isParkingFull());
    }

    @Test
    public void ownerAndTrafficCopAreNotifiedIfParkingLotIsAvailable() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        Owner owner = owner();
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = new ParkingLot(1, owner, trafficCop);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        Assertions.assertFalse(owner.isParkingFull());
        Assertions.assertFalse(trafficCop.isParkingFull());
    }
}
