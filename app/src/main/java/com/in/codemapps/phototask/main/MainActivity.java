package com.in.codemapps.phototask.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.in.codemapps.phototask.R;
import com.in.codemapps.phototask.adapter.UsersAdapter;
import com.in.codemapps.phototask.model.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContact.MainView {

    @BindView(R.id.recyclerview_users)
    RecyclerView recyclerViewUsers;

    private ProgressBar progressBar;
    MainContact.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
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

        presenter = new PresenterImpl(this, new IntractorImpl());

        presenter.requestDataFromServer();

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerViewUsers.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void setDataToRecyclerView(List<Users> usersArrayList) {
        UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, usersArrayList);
        recyclerViewUsers.setAdapter(usersAdapter);
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
