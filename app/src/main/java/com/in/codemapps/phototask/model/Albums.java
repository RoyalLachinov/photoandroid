package com.in.codemapps.phototask.model;

import com.google.gson.annotations.SerializedName;

public class Albums {

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private int id;

    @SerializedName("userId")
    private int userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
