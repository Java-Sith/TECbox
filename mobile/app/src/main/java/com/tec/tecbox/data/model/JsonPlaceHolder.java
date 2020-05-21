package com.tec.tecbox.data.model;

import com.google.gson.annotations.SerializedName;

public class JsonPlaceHolder {

    @SerializedName("userId")
    private int userid;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String tile;

    @SerializedName("completed")
    private boolean completed;

    public int getUserid() {
        return userid;
    }

    public int getId() {
        return id;
    }

    public String getTile() {
        return tile;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
