package com.example.orca.myapplication.Entity;

/**
 * Created by orca on 2017/5/23.
 */

public class WifiEntity {
    private String name;
    private int port;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
