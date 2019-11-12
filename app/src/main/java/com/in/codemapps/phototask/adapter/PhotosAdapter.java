package com.in.codemapps.phototask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.codemapps.phototask.R;
import com.in.codemapps.phototask.model.Photos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<Photos> photosList;
    private Context context;

    public PhotosAdapter(Context context, List<Photos> photosList){
        this.context = context;
        this.photosList = photosList;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.photos_card, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        holder.textViewTitle.setText(photosList.get(position).getTitle());
        Picasso.get().load(photosList.get(position).getUrl()).into(holder.imageViewURL);
    }

    @Override
    public int getItemCount() {
        if(photosList != null)
            return photosList.size();
        else
            return 0;
    }

    class PhotosViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        ImageView imageViewURL;
        private View mView;
        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            textViewTitle = mView.findViewById(R.id.phototitle);
            imageViewURL = mView.findViewById(R.id.photoimage);
        }
    }
}
