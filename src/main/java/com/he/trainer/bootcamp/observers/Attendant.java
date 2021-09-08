package com.he.trainer.bootcamp.observers;

import com.he.trainer.bootcamp.ParkingLot;
import com.he.trainer.bootcamp.Vehicle;
import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import com.he.trainer.bootcamp.exception.VehicleNotFoundException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class Attendant {
    private final List<ParkingLot> parkingLots;

    private Attendant(ParkingLot... parkingLot) {
        this.parkingLots = Arrays.asList(parkingLot);
    }

    public static Attendant attendant(ParkingLot... parkingLots) {
        return new Attendant(parkingLots);
    }

    public boolean park(Vehicle vehicle) throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        if (CollectionUtils.isNotEmpty(parkingLots)){
            for (ParkingLot parkingLot: parkingLots){
                if (parkingLot.isAvailable()){
                    return parkingLot.park(vehicle);
                }
            }
        }
        throw new ParkingBeyondCapacityException("No space to park vehicle.");
    }

    public boolean unPark(Vehicle vehicle) throws VehicleNotFoundException {
        if (CollectionUtils.isNotEmpty(parkingLots)){
            for (ParkingLot parkingLot: parkingLots){
                if (parkingLot.isParked(vehicle)) {
                    return parkingLot.unPark(vehicle);
                }
            }
        }
        throw new VehicleNotFoundException("No Vehicle found to un-park.");
    }
}
