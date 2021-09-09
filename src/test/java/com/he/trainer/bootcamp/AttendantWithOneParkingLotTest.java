package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import com.he.trainer.bootcamp.exception.VehicleNotFoundException;
import com.he.trainer.bootcamp.observers.Owner;
import com.he.trainer.bootcamp.observers.TrafficCop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.Attendant.attendant;
import static com.he.trainer.bootcamp.ParkingLot.parkingLot;
import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static com.he.trainer.bootcamp.observers.Owner.owner;
import static com.he.trainer.bootcamp.observers.TrafficCop.trafficcop;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendantWithOneParkingLotTest {

    @Test
    public void attendantHasParkedMyVehicle() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Attendant attendant = attendant(parkingLot(1));
        Vehicle vehicle = vehicle();

        assertTrue(attendant.park(vehicle));
    }

    @Test
    public void attendantHasUnParkedMyVehicle() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        Attendant attendant = attendant(parkingLot(1));
        Vehicle vehicle = vehicle();
        attendant.park(vehicle);
        assertTrue(attendant.unPark(vehicle));
    }

    @Test
    public void attendantCannotParkVehicleWhenParkingLotIsFull() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Attendant attendant = attendant(parkingLot(1));
        attendant.park(vehicle());
        Assertions.assertThrows(ParkingBeyondCapacityException.class, () -> attendant.park(vehicle()));
    }

    @Test
    public void attendantCannotParkSameVehicleMoreThanOnceIfItsAlreadyParked() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Attendant attendant = attendant(parkingLot(2));
        Vehicle vehicle = vehicle();
        attendant.park(vehicle);
        Assertions.assertThrows(VehicleAlreadyParkedException.class, () -> attendant.park(vehicle));
    }

    @Test
    public void attendantHasParkedMyVehicleAndAllObserverGetsNotifiedWhenParkingIsFull() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Owner owner = owner();
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = parkingLot(1, owner, trafficCop);

        Attendant attendant = attendant(parkingLot);
        Vehicle vehicle = vehicle();

        assertTrue(attendant.park(vehicle));
        assertTrue(owner.isParkingFull());
        assertTrue(trafficCop.isParkingFull());
    }

    @Test
    public void attendantHasUnParkedMyVehicleAndAllObserverGetsNotifiedWhenParkingIsAvailable() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        Owner owner = owner();
        TrafficCop trafficCop = trafficcop();
        Attendant attendant = attendant(parkingLot(1, owner, trafficCop));
        Vehicle vehicle = vehicle();
        attendant.park(vehicle);

        assertTrue(attendant.unPark(vehicle));
        assertFalse(owner.isParkingFull());
        assertFalse(trafficCop.isParkingFull());
    }
}
