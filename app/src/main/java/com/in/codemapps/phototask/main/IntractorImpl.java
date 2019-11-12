package com.in.codemapps.phototask.main;



import android.util.Log;

import com.in.codemapps.phototask.model.Users;
import com.in.codemapps.phototask.network.GetDataService;
import com.in.codemapps.phototask.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntractorImpl implements MainContact.Intractor {

    @Override
    public void getUserArrayList(final OnFinishedListener onFinishedListener) {
        GetDataService usersdataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Users>> usersListCall = usersdataService.getUsersList();

        String message = usersListCall.request().url().toString();
        Log.d("Retrofit: " ,message);

        usersListCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                String msg = response.body().toString();
                Log.d("Retrofit body: ", msg);
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d("Retrofit: ", t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });

    }
}
