package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.he.trainer.bootcamp.Owner.owner;
import static com.he.trainer.bootcamp.Vehicle.vehicle;

public class OwnerTest {
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
}
