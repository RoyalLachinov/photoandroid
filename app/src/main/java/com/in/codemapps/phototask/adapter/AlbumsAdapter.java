package com.in.codemapps.phototask.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.codemapps.phototask.R;
import com.in.codemapps.phototask.model.Albums;
import com.in.codemapps.phototask.photos.PhotosActivity;

import java.util.List;

import static com.in.codemapps.phototask.main.ApplicationConstants.ALBUMID;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {

    private List<Albums> albumsList;
    private Context context;

    public AlbumsAdapter(Context context, List<Albums> albumsList){
        this.context = context;
        this.albumsList = albumsList;
    }
    @NonNull
    @Override
    public AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.albums_card, parent, false);
        return new AlbumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsViewHolder holder, int position) {
        holder.textViewAlbumName.setText(albumsList.get(position).getTitle());
        int albumid = albumsList.get(position).getId();
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosActivity.class);
                intent.putExtra(ALBUMID,String.valueOf(albumid));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(albumsList != null)
            return albumsList.size();
        else
            return 0;
    }

    class AlbumsViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        TextView textViewAlbumName;
        ImageButton imageButton;
        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            imageButton = mView.findViewById(R.id.photosbutton);
            textViewAlbumName = mView.findViewById(R.id.albumname);
        }
    }
}
