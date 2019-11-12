package com.in.codemapps.phototask.network;

import com.in.codemapps.phototask.model.Albums;
import com.in.codemapps.phototask.model.Photos;
import com.in.codemapps.phototask.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("users/")
    Call<List<Users>> getUsersList();

    @GET("albums/")
    Call<List<Albums>> getAlbumsList(@Query("userId") int id);

    @GET("photos/")
    Call<List<Photos>> getPhotosList(@Query("albumId") int albumId);

}
