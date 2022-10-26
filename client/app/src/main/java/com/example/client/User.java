package com.example.client;

public class User {

    private String _id;
    private String nic;
    private String password;
    private String name;
    private String userType;

    public User(String id, String nic, String password, String name, String userType) {
        this._id = id;
        this.nic = nic;
        this.password = password;
        this.name = name;
        this.userType = userType;
    }

    public String getUserId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
