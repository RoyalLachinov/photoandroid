package com.in.codemapps.phototask.albums;

import android.util.Log;

import com.in.codemapps.phototask.main.MainContact;
import com.in.codemapps.phototask.model.Albums;
import com.in.codemapps.phototask.network.GetDataService;
import com.in.codemapps.phototask.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsIntractorImpl implements MainContact.AlbumsIntractor {
    @Override
    public void getAlbumsList(OnFinishedListener onFinishedListener, int id) {
        GetDataService albumsDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Albums>> listCall = albumsDataService.getAlbumsList(id);
        String message = listCall.request().url().toString();
        Log.d("Retrofit Albums:", message);

        listCall.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                String msg = response.body().toString();
                Log.d("Retrofit body: ", msg);
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                Log.d("Retrofit Albums: ", t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
