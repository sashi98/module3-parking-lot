package com.he.trainer.bootcamp.exception;

public class ParkingBeyondCapacityException extends Throwable {
    public ParkingBeyondCapacityException(String msg) {
        System.out.println("ParkingBeyondCapacityException: "+msg);
    }
}
