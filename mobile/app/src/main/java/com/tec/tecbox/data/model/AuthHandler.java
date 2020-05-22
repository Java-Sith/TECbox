package com.tec.tecbox.data.model;

import com.google.gson.annotations.SerializedName;

public class AuthHandler {

    @SerializedName("type")
    private int type;

    @SerializedName("cedula")
    private int cedula;

    @SerializedName("pass")
    private String pass;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("key")
    private String key;

    public AuthHandler(int type, int cedula, String pass) {
        this.type = type;
        this.cedula = cedula;
        this.pass = pass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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