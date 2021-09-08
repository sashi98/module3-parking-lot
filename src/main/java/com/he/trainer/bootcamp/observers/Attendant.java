package com.he.trainer.bootcamp.observers;

import com.he.trainer.bootcamp.ParkingLot;
import com.he.trainer.bootcamp.Vehicle;
import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.UnParkingFromEmptyLotException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeParkedException;
import com.he.trainer.bootcamp.exception.VehicleCouldNotBeUnParkedException;

public class Attendant {
    private final ParkingLot parkingLot;

    private Attendant(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public static Attendant attendant(ParkingLot parkingLot) {
        return new Attendant(parkingLot);
    }

    public boolean park(Vehicle vehicle) throws VehicleCouldNotBeParkedException, ParkingBeyondCapacityException {
        return parkingLot.park(vehicle);
    }

    public boolean unPark(Vehicle vehicle) throws VehicleCouldNotBeUnParkedException, UnParkingFromEmptyLotException {
        return parkingLot.unPark(vehicle);
    }
}
