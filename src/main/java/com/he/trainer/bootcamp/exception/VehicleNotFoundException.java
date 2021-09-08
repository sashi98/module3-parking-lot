package com.he.trainer.bootcamp.exception;

public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(String msg){
        System.out.println("VehicleNotFoundException: "+msg);
    }
}
