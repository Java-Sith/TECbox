package com.tec.tecbox.data.model;

import com.google.gson.annotations.SerializedName;

public class ResponserHandler {

    @SerializedName("id")
    private int id;

    @SerializedName("repartidor")
    private int repartidor;

    @SerializedName("estado")
    private int estado;

    @SerializedName("result")
    private String result;

    public ResponserHandler(int id, int repartidor, int estado) {
        this.id = id;
        this.repartidor = repartidor;
        this.estado = estado;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(int repartidor) {
        this.repartidor = repartidor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
