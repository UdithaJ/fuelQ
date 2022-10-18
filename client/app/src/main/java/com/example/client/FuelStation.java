package com.example.client;

public class FuelStation {

    private String id;
    private String nic;
    private String name;
    private String permit_no;
    private String address;
    private String password;

    public FuelStation(String id, String nic, String name, String permit_no, String address, String password) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.permit_no = permit_no;
        this.address = address;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermit_no() {
        return permit_no;
    }

    public void setPermit_no(String permit_no) {
        this.permit_no = permit_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
