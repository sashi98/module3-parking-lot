package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.UnParkingFromEmptyLotException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import com.he.trainer.bootcamp.observers.Attendant;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static com.he.trainer.bootcamp.observers.Attendant.attendant;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendantWithOneParkingLotTest {

    @Test
    public void attendantHasParkedMyVehicle() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = new ParkingLot(1);
        Attendant attendant = attendant(parkingLot);
        Vehicle vehicle = vehicle();

        assertTrue(attendant.park(vehicle));
    }

    @Test
    public void attendantHasUnParkedMyVehicle() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Attendant attendant = attendant(parkingLot);
        Vehicle vehicle = vehicle();
        attendant.park(vehicle);
        assertTrue(attendant.unPark(vehicle));
    }
}
