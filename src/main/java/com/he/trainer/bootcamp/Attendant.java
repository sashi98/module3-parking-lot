package com.he.trainer.bootcamp;

import com.he.trainer.bootcamp.exception.ParkingBeyondCapacityException;
import com.he.trainer.bootcamp.exception.VehicleAlreadyParkedException;
import com.he.trainer.bootcamp.exception.VehicleNotFoundException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class Attendant {
    private ParkingLotSelectionPolicy lotSelectionPolicy;
    private final List<ParkingLot> parkingLots;
    private final List<ParkingLot> availableParkingLots;

    private Attendant(ParkingLot... parkingLots) {
        this.lotSelectionPolicy = ParkingLotSelectionPolicy.FIRST_AVAILABLE_LOT;
        this.parkingLots = new ArrayList<>(Arrays.asList(parkingLots));
        this.availableParkingLots = new ArrayList<>(Arrays.asList(parkingLots));
    }

    public static Attendant attendant(ParkingLot... parkingLots) {
        return new Attendant(parkingLots);
    }

    public boolean park(Vehicle vehicle) throws VehicleAlreadyParkedException, ParkingBeyondCapacityException {
        if (CollectionUtils.isNotEmpty(availableParkingLots)) {
            availableParkingLots.removeIf(ParkingLot::isFull);
            ParkingLot selectedLot = selectedLot();
            if (Objects.nonNull(selectedLot)) {
                return selectedLot.park(vehicle);
            }
        }
        throw new ParkingBeyondCapacityException("No space to park vehicle.");
    }

    private ParkingLot selectedLot() {
        if (CollectionUtils.isNotEmpty(availableParkingLots)) {
            return lotSelectionPolicy.selectLot(availableParkingLots);
        }
        return null;
    }

    public boolean unPark(Vehicle vehicle) throws VehicleNotFoundException {
        if (CollectionUtils.isNotEmpty(parkingLots)) {
            for (ParkingLot parkingLot : parkingLots) {
                if (parkingLot.isParked(vehicle)) {
                    parkingLot.unPark(vehicle);
                    if (!availableParkingLots.contains(parkingLot)) {
                        availableParkingLots.add(parkingLot);
                    }
                    return true;
                }
            }
        }
        throw new VehicleNotFoundException("No Vehicle found to un-park.");
    }

    public void overrideLotSelectionPolicy(ParkingLotSelectionPolicy lotSelectionPolicy) {
        this.lotSelectionPolicy = lotSelectionPolicy;
    }
}
