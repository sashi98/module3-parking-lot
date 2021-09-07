package com.he.trainer.bootcamp;

public class UnParkingFromEmptyLotException extends Throwable {
    public UnParkingFromEmptyLotException(String msg) {
        System.out.println("UnParkingFromEmptyLotException: "+msg);
    }
}
