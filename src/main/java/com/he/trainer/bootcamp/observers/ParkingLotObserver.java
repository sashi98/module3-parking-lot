package com.he.trainer.bootcamp.observers;

public interface ParkingLotObserver {
    public void parkingIsFullNotification();
    public void parkingIsAvailableNotification();
    public boolean isParkingFull();
}
