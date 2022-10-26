package com.example.client;

public class QueueClass {
    private String id;
    private String fuelType;
    private String vehicleType;
    private Integer amount;

    public QueueClass(String id, String fuelType, String vehicleType, Integer amount) {
        this.id = id;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer vehicleType) {
        this.amount = amount;
    }

}
