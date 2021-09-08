package com.he.trainer.bootcamp.exception;

public class VehicleAlreadyParkedException extends Exception {
    public VehicleAlreadyParkedException(String msg){
        System.out.println("VehicleAlreadyParkedException: "+msg);
    }
}
