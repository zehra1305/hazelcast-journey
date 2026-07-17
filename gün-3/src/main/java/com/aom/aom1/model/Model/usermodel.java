package com.aom.aom1.model.Model;

import java.io.Serializable;

public class usermodel implements Serializable {
    private String phoneNumber;
    private boolean available;
    public usermodel() {}
    public usermodel(String phoneNumber, boolean available) {
        this.phoneNumber = phoneNumber;
        this.available = available;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
