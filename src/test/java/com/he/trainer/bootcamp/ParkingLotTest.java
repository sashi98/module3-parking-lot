package com.he.trainer.bootcamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    public void one_vehicle_can_be_parked(){
        ParkingLot parkingLot = new ParkingLot();
        Vehicle vehicle = new Vehicle();
        boolean parked = parkingLot.park(vehicle);
        Assertions.assertTrue(parked);
    }

    @Test
    public void one_vehicle_can_be_unparked(){
        ParkingLot parkingLot = new ParkingLot();
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        boolean unParked = parkingLot.unPark(vehicle);
        Assertions.assertTrue(unParked);
    }
}
