package com.example.client;

public class Driver {
    private String nic;
    private String password;
    private String vehicleNumber;
    private String fuelType;
    private String vehicleType;

    public Driver(String nic, String password, String vehicleNumber, String fuelType, String vehicleType) {
        this.nic = nic;
        this.password = password;
        this.vehicleNumber = vehicleNumber;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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


}
