package com.he.trainer.bootcamp.observers;

public class Owner implements ParkingLotObserver {
    private boolean parkingIsFull;

    @Override
    public void parkingIsFullNotification() {
        parkingIsFull = true;
    }

    @Override
    public void parkingIsAvailableNotification() {
        parkingIsFull = false;
    }

    @Override
    public boolean isParkingFull() {
        return parkingIsFull;
    }

    public static Owner owner() {
        return new Owner();
    }
}
