package com.in.codemapps.phototask.main;

import com.in.codemapps.phototask.model.Albums;
import com.in.codemapps.phototask.model.Photos;
import com.in.codemapps.phototask.model.Users;

import java.util.ArrayList;
import java.util.List;

public interface MainContact {

    interface Presenter{

        void onDestroy();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Users> usersArrayList);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * This is for fetching data from data source (Users).
     **/
    interface Intractor {

        interface OnFinishedListener {
            void onFinished(List<Users> usersArrayList);
            void onFailure(Throwable t);
        }

        void getUserArrayList(OnFinishedListener onFinishedListener);
    }

    /**
     * This is for fetching data from data source (Photos).
     **/
    interface PhotosIntractor{

        interface OnFinishedListener {
            void onFinished(List<Photos> photosList);
            void onFailure(Throwable t);
        }

        void getPhotosList(OnFinishedListener onFinishedListener, int id);
    }

    interface PhotosPresenter{

        void onDestroy();

        void requestDataFromServer(int id);

    }

    interface PhotosMainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Photos> photosList);

        void onResponseFailure(Throwable throwable);
    }

    /**
     * This Intractor, Presenter and View is for Albums
     **/
    interface AlbumsIntractor{

        interface OnFinishedListener {
            void onFinished(List<Albums> albumsList);
            void onFailure(Throwable t);
        }

        void getAlbumsList(OnFinishedListener onFinishedListener, int id);
    }

    interface AlbumsPresenter{

        void onDestroy();

        void requestDataFromServer(int id);

    }

    interface AlbumsView{
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Albums> albumsList);

        void onResponseFailure(Throwable throwable);
    }

}
