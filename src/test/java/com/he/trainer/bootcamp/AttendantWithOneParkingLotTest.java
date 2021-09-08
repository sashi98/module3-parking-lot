package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.UnParkingFromEmptyLotException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;
import com.he.trainer.bootcamp.observers.Attendant;
import com.he.trainer.bootcamp.observers.Owner;
import com.he.trainer.bootcamp.observers.TrafficCop;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static com.he.trainer.bootcamp.observers.Attendant.attendant;
import static com.he.trainer.bootcamp.observers.Owner.owner;
import static com.he.trainer.bootcamp.observers.TrafficCop.trafficcop;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void attendantHasParkedMyVehicleAndAllObserverGetsNotifiedWhenParkingIsFull() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        Owner owner = owner();
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = new ParkingLot(1, owner, trafficCop);

        Attendant attendant = attendant(parkingLot);
        Vehicle vehicle = vehicle();

        assertTrue(attendant.park(vehicle));
        assertTrue(owner.isParkingFull());
        assertTrue(trafficCop.isParkingFull());
    }

    @Test
    public void attendantHasUnParkedMyVehicleAndAllObserverGetsNotifiedWhenParkingIsAvailable() throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException, VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        Owner owner = owner();
        TrafficCop trafficCop = trafficcop();
        ParkingLot parkingLot = new ParkingLot(1, owner, trafficCop);

        Attendant attendant = attendant(parkingLot);
        Vehicle vehicle = vehicle();
        attendant.park(vehicle);

        assertTrue(attendant.unPark(vehicle));
        assertFalse(owner.isParkingFull());
        assertFalse(trafficCop.isParkingFull());
    }
}
