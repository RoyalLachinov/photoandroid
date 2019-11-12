package com.in.codemapps.phototask.main;

import com.in.codemapps.phototask.model.Users;

import java.util.ArrayList;
import java.util.List;

public class PresenterImpl implements MainContact.Presenter, MainContact.Intractor.OnFinishedListener {

    private MainContact.MainView mainView;
    private MainContact.Intractor intractor;

    public PresenterImpl(MainContact.MainView mainView, MainContact.Intractor intractor){
        this.intractor = intractor;
        this.mainView = mainView;
    }

    @Override
    public void onDestroy() {
        this.mainView = null;
    }

    @Override
    public void requestDataFromServer() {
        intractor.getUserArrayList(this);
    }

    @Override
    public void onFinished(List<Users> usersArrayList) {
        if(this.mainView !=null){
            mainView.setDataToRecyclerView(usersArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(this.mainView !=null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
