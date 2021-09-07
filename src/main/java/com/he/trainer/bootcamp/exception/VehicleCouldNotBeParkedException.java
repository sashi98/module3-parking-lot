package com.he.trainer.bootcamp.exception;

public class VehicleCouldNotBeParkedException extends Exception {
    public VehicleCouldNotBeParkedException(String msg){
        System.out.println("VehicleCouldNotBeParkedException: "+msg);
    }
}
