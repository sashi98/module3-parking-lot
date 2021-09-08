package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void one_vehicle_can_be_unparked() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.unPark(vehicle));
    }

    @Test
    public void same_vehicle_should_not_be_unparked_twice_unless_it_was_parked() throws VehicleCouldNotBeUnParkedException, VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, UnParkingFromEmptyLotException {
        ParkingLot parkingLot = new ParkingLot(3);
        Vehicle v1 = vehicle();
        Vehicle v2 = vehicle();
        Vehicle v3 = vehicle();

        parkingLot.park(v1);
        parkingLot.park(v2);
        parkingLot.park(v3);

        parkingLot.unPark(v1);

        assertThrows(VehicleCouldNotBeUnParkedException.class, () -> parkingLot.unPark(v1));
    }

    @Test
    public void vehicleUnParkingFromEmptyLotThrowsException() throws VehicleCouldNotBeUnParkedException, VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, UnParkingFromEmptyLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        assertThrows(UnParkingFromEmptyLotException.class, () -> parkingLot.unPark(vehicle));
    }

    @Test
    public void knowMyVehicleIsParked() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        boolean parked = parkingLot.isParked(vehicle);

        Assertions.assertTrue(parked);
    }

    @Test
    public void knowMyVehicleIsNotParked() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        boolean parked = parkingLot.isParked(vehicle);

        Assertions.assertFalse(parked);
    }
}
