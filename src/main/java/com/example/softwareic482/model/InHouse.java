package com.example.softwareic482.model;

/**
 * Same as Outsourced. Allows us to initialize a part with a machineID.
 */
public class InHouse extends Part {

    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineId() {
        return String.valueOf(machineId);
    }

}