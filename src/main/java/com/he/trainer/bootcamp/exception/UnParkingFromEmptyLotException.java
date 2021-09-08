package com.he.trainer.bootcamp.exception;

public class UnParkingFromEmptyLotException extends Throwable {
    public UnParkingFromEmptyLotException(String msg) {
        System.out.println("UnParkingFromEmptyLotException: "+msg);
    }
}
