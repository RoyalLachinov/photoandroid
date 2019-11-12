package com.in.codemapps.phototask.model;

import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("id")
    private int id;

    @SerializedName("albumId")
    private int albumId;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
