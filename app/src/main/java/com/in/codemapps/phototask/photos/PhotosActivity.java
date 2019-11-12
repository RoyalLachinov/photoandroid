package com.in.codemapps.phototask.photos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.in.codemapps.phototask.R;
import com.in.codemapps.phototask.adapter.PhotosAdapter;
import com.in.codemapps.phototask.main.MainContact;
import com.in.codemapps.phototask.model.Photos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.in.codemapps.phototask.main.ApplicationConstants.ALBUMID;

public class PhotosActivity extends AppCompatActivity implements MainContact.PhotosMainView {

    @BindView(R.id.recyclerview_photos)
    RecyclerView recyclerViewPhotos;

    private ProgressBar progressBar;

    MainContact.PhotosPresenter presenter;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.photo));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlack));
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        if(!checkInternetConn(this)){

            AlertDialog.Builder alertDialogInternet=new AlertDialog.Builder(this);
            alertDialogInternet.setMessage("Oops!! No Internet Connection");
            alertDialogInternet.setTitle("Unable to Connect").setCancelable(false).setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i1=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(i1);
                }
            }).setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            AlertDialog dialog=alertDialogInternet.create();
            dialog.show();

        }

        RecyclerViewInit();
        progressBarInit();

        id = getIntent().getStringExtra(ALBUMID);

        presenter = new PhotosPresenterImpl(this, new PhotosIntractorImpl());
        presenter.requestDataFromServer(Integer.parseInt(id));
    }


    public static boolean checkInternetConn(Context context){
        ConnectivityManager cmr=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cmr.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isAvailable() && networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }

    private void progressBarInit(){
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );

        progressBar.setVisibility(View.VISIBLE);

        this.addContentView(relativeLayout, params);
    }

    private void RecyclerViewInit(){
        recyclerViewPhotos.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewPhotos.setLayoutManager(layoutManager);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(List<Photos> photosList) {
        PhotosAdapter photosAdapter = new PhotosAdapter(PhotosActivity.this, photosList);
        recyclerViewPhotos.setAdapter(photosAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, "Connection Error: "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
