package com.tec.tecbox.data.model;

import com.google.gson.annotations.SerializedName;

public class AuthHandler {

    @SerializedName("type")
    private int type;

    @SerializedName("cedula")
    private int cedula;

    @SerializedName("pass")
    private String pass;

    public int getCedula() {
        return cedula;
    }

    public int getType() {
        return type;
    }

    public String getPass() {
        return pass;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setType(int type) {
        this.type = type;
    }
}