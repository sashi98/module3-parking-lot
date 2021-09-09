package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.he.trainer.bootcamp.Attendant.attendant;
import static com.he.trainer.bootcamp.ParkingLot.parkingLot;
import static com.he.trainer.bootcamp.ParkingLotSelectionPolicy.LEAST_VEHICLE_LOT;
import static com.he.trainer.bootcamp.ParkingLotSelectionPolicy.MOST_CAPACITY_LOT;
import static com.he.trainer.bootcamp.Vehicle.vehicle;

public class AttendantParkingSelectionPolicyTest {

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

    @Test
    public void attendantParkingVehiclesInAvailableLotHavingMostCapacityOfVehicles() throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot1 = getParkingLotWithVehicles(0, 2);
        ParkingLot parkingLot2 = getParkingLotWithVehicles(1, 3);
        ParkingLot parkingLot3 = getParkingLotWithVehicles(2, 6);

        Attendant attendant = attendant(parkingLot1, parkingLot2, parkingLot3);
        attendant.overrideLotSelectionPolicy(MOST_CAPACITY_LOT);
        attendant.park(vehicle());
        attendant.park(vehicle());
        attendant.park(vehicle());

        Assertions.assertEquals(0, parkingLot1.vehicleCount());
        Assertions.assertEquals(1, parkingLot2.vehicleCount());
        Assertions.assertEquals(5, parkingLot3.vehicleCount());
    }

    private ParkingLot getParkingLotWithVehicles(int count, int capacity) throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        ParkingLot parkingLot = parkingLot(capacity);
        for (int i = 0; i < count; i++) {
            parkingLot.park(vehicle());
        }
        return parkingLot;
    }
}
