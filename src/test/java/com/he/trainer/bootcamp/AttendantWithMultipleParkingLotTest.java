package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import com.he.trainer.bootcamp.exception.VehicleNotFoundException;
import com.he.trainer.bootcamp.observers.Owner;
import com.he.trainer.bootcamp.observers.TrafficCop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.ParkingLot.parkingLot;
import static com.he.trainer.bootcamp.ParkingLotSelectionPolicy.LEAST_VEHICLE_LOT;
import static com.he.trainer.bootcamp.Vehicle.vehicle;
import static com.he.trainer.bootcamp.Attendant.attendant;
import static com.he.trainer.bootcamp.observers.Owner.owner;
import static com.he.trainer.bootcamp.observers.TrafficCop.trafficcop;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendantWithMultipleParkingLotTest {

    @Test
    public void attendantHasParkedMyVehicle() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Attendant attendant = attendant(parkingLot(1), parkingLot(1));
        Vehicle vehicle = vehicle();

        assertTrue(attendant.park(vehicle));
    }


    @Test
    public void attendantHasUnParkedMyVehicle() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        Attendant attendant = attendant(parkingLot(1), parkingLot(1));
        Vehicle vehicle = vehicle();

        attendant.park(vehicle);

        assertTrue(attendant.unPark(vehicle));
    }

    @Test
    public void attendantCannotParkVehicleWhenAllParkingLotIsFull() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Attendant attendant = attendant(parkingLot(1), parkingLot(1));

        attendant.park(vehicle());
        attendant.park(vehicle());

        Assertions.assertThrows(ParkingBeyondCapacityException.class, () -> attendant.park(vehicle()));
    }

    @Test
    public void attendantCannotParkSameVehicleMoreThanOnceIfItsAlreadyParked() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Attendant attendant = attendant(parkingLot(2), parkingLot(2));
        Vehicle vehicle = vehicle();

        attendant.park(vehicle);

        Assertions.assertThrows(VehicleAlreadyParkedException.class, () -> attendant.park(vehicle));
    }

    @Test
    public void attendantParkingVehiclesUntilAllLotsFullAndAllObserverGetsNotifiedForParkingIsFull() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        Owner owner1 = owner();
        Owner owner2 = owner();
        TrafficCop trafficCop1 = trafficcop();
        TrafficCop trafficCop2 = trafficcop();

        Attendant attendant = attendant(parkingLot(1, owner1, trafficCop1), parkingLot(1, owner2, trafficCop2));
        Vehicle vehicle = vehicle();

        attendant.park(vehicle);
        attendant.park(vehicle);

        assertTrue(owner1.isParkingFull());
        assertTrue(trafficCop1.isParkingFull());
        assertTrue(owner2.isParkingFull());
        assertTrue(trafficCop2.isParkingFull());
    }

    @Test
    public void attendantHasUnParkedMyVehicleAndAllObserverGetsNotifiedWhenParkingIsAvailable() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException, VehicleNotFoundException {
        Owner owner1 = owner();
        Owner owner2 = owner();
        TrafficCop trafficCop1 = trafficcop();
        TrafficCop trafficCop2 = trafficcop();
        Attendant attendant = attendant(parkingLot(1, owner1, trafficCop1), parkingLot(1, owner2, trafficCop2));
        Vehicle vehicle = vehicle();

        attendant.park(vehicle);
        attendant.unPark(vehicle);

        assertFalse(owner1.isParkingFull());
        assertFalse(trafficCop1.isParkingFull());
        assertFalse(owner2.isParkingFull());
        assertFalse(trafficCop2.isParkingFull());
    }

    @Test
    public void attendantParkingVehiclesInAvailableLot() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot1 = getParkingLotWithVehicles(4, 5);
        ParkingLot parkingLot2 = getParkingLotWithVehicles(1, 5);
        ParkingLot parkingLot3 = getParkingLotWithVehicles(3, 5);

        Attendant attendant = attendant(parkingLot1, parkingLot2, parkingLot3);
        attendant.park(vehicle());
        attendant.park(vehicle());
        attendant.park(vehicle());

        Assertions.assertEquals(5, parkingLot1.vehicleCount());
        Assertions.assertEquals(3, parkingLot2.vehicleCount());
        Assertions.assertEquals(3, parkingLot3.vehicleCount());
    }

    @Test
    public void attendantParkingVehiclesInAvailableLotHavingLeastNumberOfVehicles() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot1 = getParkingLotWithVehicles(4, 6);
        ParkingLot parkingLot2 = getParkingLotWithVehicles(1, 6);
        ParkingLot parkingLot3 = getParkingLotWithVehicles(3, 6);

        Attendant attendant = attendant(parkingLot1, parkingLot2, parkingLot3);
        attendant.overrideLotSelectionPolicy(LEAST_VEHICLE_LOT);
        attendant.park(vehicle());
        attendant.park(vehicle());
        attendant.park(vehicle());

        Assertions.assertEquals(4, parkingLot1.vehicleCount());
        Assertions.assertEquals(4, parkingLot2.vehicleCount());
        Assertions.assertEquals(3, parkingLot3.vehicleCount());
    }

    private ParkingLot getParkingLotWithVehicles(int count, int capacity) throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot1 = parkingLot(capacity);
        for (int i = 0; i < count; i++) {
            parkingLot1.park(vehicle());
        }
        return parkingLot1;
    }
}
