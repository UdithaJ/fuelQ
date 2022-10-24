package com.example.client;

public class FuelInventory {

    private String id;
    private String stationId;
    private String fuelTypeId;
    private Integer currentCapacirt;

    public FuelInventory(String id, String stationId, String fuelTypeId, Integer currentCapacirt) {
        this.id = id;
        this.stationId = stationId;
        this.fuelTypeId = fuelTypeId;
        this.currentCapacirt = currentCapacirt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(String fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public Integer getCurrentCapacirt() {
        return currentCapacirt;
    }

    public void setCurrentCapacirt(Integer currentCapacirt) {
        this.currentCapacirt = currentCapacirt;
    }
}
