package com.he.trainer.bootcamp;

import org.apache.commons.lang.RandomStringUtils;

public class Vehicle {
    private final int vid;

    public Vehicle() {
        this.vid = this.hashCode();
    }

    public static Vehicle vehicle(){
        return new Vehicle();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vid=" + vid +
                '}';
    }
}
