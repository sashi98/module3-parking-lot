package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import com.he.trainer.bootcamp.exception.VehicleNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.ParkingLot.parkingLot;
import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotTest {
    @Test
    public void one_vehicle_can_be_parked() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = parkingLot(1);
        Vehicle vehicle = vehicle();

        assertTrue(parkingLot.park(vehicle));
    }

    @Test
    public void same_vehicle_should_not_be_parked_twice_unless_it_was_unparked() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = parkingLot(2);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    public void vehicleParkingBeyondCapacityThrowsException() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = parkingLot(1);
        parkingLot.park(vehicle());

        assertThrows(ParkingBeyondCapacityException.class, () -> parkingLot.park(vehicle()));
    }

    @Test
    public void one_vehicle_can_be_unparked() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        ParkingLot parkingLot = parkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.unPark(vehicle));
    }

    @Test
    public void same_vehicle_should_not_be_unparked_twice_unless_it_was_parked() throws VehicleNotFoundException, VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = parkingLot(3);
        Vehicle v1 = vehicle();
        Vehicle v2 = vehicle();
        Vehicle v3 = vehicle();

        parkingLot.park(v1);
        parkingLot.park(v2);
        parkingLot.park(v3);

        parkingLot.unPark(v1);

        assertThrows(VehicleNotFoundException.class, () -> parkingLot.unPark(v1));
    }

    @Test
    public void vehicleUnParkingFromEmptyLotThrowsException() throws VehicleNotFoundException, VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = parkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        assertThrows(VehicleNotFoundException.class, () -> parkingLot.unPark(vehicle));
    }

    @Test
    public void knowMyVehicleIsParked() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = parkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);

        boolean parked = parkingLot.isParked(vehicle);

        Assertions.assertTrue(parked);
    }

    @Test
    public void knowMyVehicleIsNotParked() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        ParkingLot parkingLot = parkingLot(1);
        Vehicle vehicle = vehicle();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        boolean parked = parkingLot.isParked(vehicle);

        Assertions.assertFalse(parked);
    }
}
