package com.example.day_2;

import java.io.Serializable;

public class Dto implements Serializable {
    private String adi;
    private String soyadi;
    private String telefon;
    private String email;
    private int yas;

    public Dto() {}
    public Dto(String adi, String soyadi, String telefon, String email, int yas) {
        this.adi = adi;
        this.soyadi = soyadi;
        this.telefon = telefon;
        this.email = email;
        this.yas = yas;
    }
    public String getAdi() {
        return adi;
    }
    public void setAdi(String adi) {
        this.adi = adi;
    }
    public String getSoyadi() {
        return soyadi;
    }
    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }
    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getYas() {
        return yas;
    }
    public void setYas(int yas) {
        this.yas = yas;
    }

}
