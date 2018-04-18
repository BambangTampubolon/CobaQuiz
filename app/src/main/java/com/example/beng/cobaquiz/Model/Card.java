package com.example.beng.cobaquiz.Model;

import java.io.Serializable;

/**
 * Created by Beng on 2/20/2018.
 */

public class Card implements Serializable{
    private int idCard;
    private int value;
    private int jenis;
    private String tampilan;

    public String getTampilan() {
        return tampilan;
    }

    public void setTampilan(String tampilan) {
        this.tampilan = tampilan;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getJenis() {
        return this.jenis;
    }

    public void setJenis(int jenis) {
        this.jenis = jenis;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
