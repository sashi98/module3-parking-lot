package com.he.trainer.bootcamp.observers;

public class TrafficCop implements ParkingLotObserver{
    private boolean parkingIsFull;

    @Override
    public void parkingIsFullNotification() {
        parkingIsFull = true;
    }

    @Override
    public void parkingIsAvailableNotification() {
        parkingIsFull = false;
    }

    public boolean isParkingFull() {
        return parkingIsFull;
    }

    public static TrafficCop trafficcop() {
        return new TrafficCop();
    }
}
