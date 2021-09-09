package com.he.trainer.bootcamp;

import java.util.Comparator;
import java.util.List;

public enum ParkingLotSelectionPolicy {
    FIRST_AVAILABLE_LOT(){
        @Override
        public ParkingLot selectLot(List<ParkingLot> availableParkingLots) {
            return availableParkingLots.get(0);
        }
    },
    LEAST_VEHICLE_LOT(){
        @Override
        public ParkingLot selectLot(List<ParkingLot> availableParkingLots) {
            availableParkingLots.sort(Comparator.comparing(ParkingLot::vehicleCount));
            return availableParkingLots.get(0);
        }
    };

    public abstract ParkingLot selectLot(List<ParkingLot> availableParkingLots);
}
