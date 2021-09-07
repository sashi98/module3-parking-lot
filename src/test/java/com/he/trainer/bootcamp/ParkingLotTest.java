package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotTest {
    @Test
    public void one_vehicle_can_be_parked() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = vehicle();

        assertTrue(parkingLot.park(vehicle));
    }

    @Test
    public void same_vehicle_should_not_be_parked_twice_unless_it_was_unparked() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        assertThrows(VehicleCouldNotBeParkedException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    public void vehicleParkingBeyondCapacityThrowsException() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(vehicle());

        assertThrows(ParkingBeyondCapacityException.class, () -> parkingLot.park(vehicle()));
    }

}
