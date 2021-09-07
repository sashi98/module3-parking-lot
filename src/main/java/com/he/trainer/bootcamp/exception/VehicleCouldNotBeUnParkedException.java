package com.he.trainer.bootcamp.exception;

public class VehicleCouldNotBeUnParkedException extends Exception {
    public VehicleCouldNotBeUnParkedException(String msg){
        System.out.println("VehicleCouldNotBeUnParkedException: "+msg);
    }
}
