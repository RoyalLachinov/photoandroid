package com.in.codemapps.phototask.albums;

import com.in.codemapps.phototask.main.MainContact;
import com.in.codemapps.phototask.model.Albums;

import java.util.List;

public class AlbumsPresenterImpl implements MainContact.AlbumsPresenter, MainContact.AlbumsIntractor.OnFinishedListener {

   MainContact.AlbumsView albumsView;
    MainContact.AlbumsIntractor intractor;

    public AlbumsPresenterImpl(MainContact.AlbumsView albumsView, MainContact.AlbumsIntractor intractor){
        this.albumsView = albumsView;
        this.intractor = intractor;
    }

    @Override
    public void onDestroy() {
        this.albumsView = null;
    }

    @Override
    public void requestDataFromServer(int id) {
        intractor.getAlbumsList(this, id);
    }

    @Override
    public void onFinished(List<Albums> albumsList) {
        if(this.albumsView != null){
            albumsView.setDataToRecyclerView(albumsList);
            albumsView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(this.albumsView != null){
            albumsView.onResponseFailure(t);
            albumsView.hideProgress();
        }
    }
}
