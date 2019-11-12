package com.in.codemapps.phototask.photos;

import com.in.codemapps.phototask.main.MainContact;
import com.in.codemapps.phototask.model.Photos;

import java.util.List;

public class PhotosPresenterImpl implements MainContact.PhotosPresenter, MainContact.PhotosIntractor.OnFinishedListener {

    MainContact.PhotosMainView photosMainView;
    MainContact.PhotosIntractor photosIntractor;

    public PhotosPresenterImpl(MainContact.PhotosMainView photosMainView, MainContact.PhotosIntractor photosIntractor)
    {
        this.photosIntractor = photosIntractor;
        this.photosMainView = photosMainView;
    }
    @Override
    public void onFinished(List<Photos> photosList) {
        if(this.photosMainView != null){
            photosMainView.setDataToRecyclerView(photosList);
            photosMainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(this.photosMainView != null){
            photosMainView.onResponseFailure(t);
            photosMainView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        this.photosMainView = null;
    }

    @Override
    public void requestDataFromServer(int id) {
        photosIntractor.getPhotosList(this, id);
    }
}
