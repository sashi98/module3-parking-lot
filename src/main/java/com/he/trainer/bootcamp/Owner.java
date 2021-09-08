package com.he.trainer.bootcamp;

public class Owner {
    private boolean parkingIsFull;

    public void parkingIsFullNotification(){
        parkingIsFull = true;
    }
    public void parkingIsAvailableNotification() {
        parkingIsFull = false;
    }

    public boolean isParkingFull() {
        return parkingIsFull;
    }

    public static Owner owner() {
        return new Owner();
    }
}
