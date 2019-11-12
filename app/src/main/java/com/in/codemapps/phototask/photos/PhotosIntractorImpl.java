package com.in.codemapps.phototask.photos;

import android.util.Log;

import com.in.codemapps.phototask.main.MainContact;
import com.in.codemapps.phototask.model.Photos;
import com.in.codemapps.phototask.network.GetDataService;
import com.in.codemapps.phototask.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosIntractorImpl implements MainContact.PhotosIntractor {
    @Override
    public void getPhotosList(OnFinishedListener onFinishedListener, int id) {
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Photos>> listCall = getDataService.getPhotosList(id);
        String message = listCall.request().url().toString();
        Log.d("Retrofit Albums:", message);

        listCall.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                String msg = response.body().toString();
                Log.d("Retrofit body: ", msg);
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Log.d("Retrofit Photos: ", t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
