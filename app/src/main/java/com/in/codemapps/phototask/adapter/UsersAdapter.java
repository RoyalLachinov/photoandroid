package com.in.codemapps.phototask.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.codemapps.phototask.R;
import com.in.codemapps.phototask.albums.AlbumsActivity;
import com.in.codemapps.phototask.model.Users;

import java.util.ArrayList;
import java.util.List;

import static com.in.codemapps.phototask.main.ApplicationConstants.USERID;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<Users> usersArrayList;
    private Context context;

    public UsersAdapter(Context context, List<Users> usersArrayList){
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.users_card, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.textViewName.setText(usersArrayList.get(position).getName());
        holder.textViewEmail.setText(usersArrayList.get(position).getEmail());
        holder.textViewUserName.setText(usersArrayList.get(position).getUsername());
        holder.textViewAddress.setText(usersArrayList.get(position).getAddressList().getStreet()+ ", " +
                usersArrayList.get(position).getAddressList().getSuite()+ ", "+
                usersArrayList.get(position).getAddressList().getCity()+ ", " +
                usersArrayList.get(position).getAddressList().getZipcode());
        holder.textViewPhone.setText(usersArrayList.get(position).getPhone());
        holder.textViewWebsite.setText(usersArrayList.get(position).getWebsite());
        holder.textViewCompanyName.setText(usersArrayList.get(position).getCompany().getName());
        holder.textViewCompanyPhrase.setText(usersArrayList.get(position).getCompany().getCatchPhrase());
        int id = usersArrayList.get(position).getId();
        holder.buttonAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumsActivity.class);
                intent.putExtra(USERID, String.valueOf(id));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(usersArrayList != null)
            return usersArrayList.size();
        else
            return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        private TextView textViewName, textViewEmail, textViewUserName, textViewAddress, textViewPhone, textViewWebsite,
                textViewCompanyName, textViewCompanyPhrase;

        private Button buttonAlbum;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            buttonAlbum = mView.findViewById(R.id.albumsbutton);
            textViewName = mView.findViewById(R.id.username);
            textViewEmail = mView.findViewById(R.id.useremail);
            textViewUserName = mView.findViewById(R.id.userusername);
            textViewAddress = mView.findViewById(R.id.useraddress);
            textViewPhone = mView.findViewById(R.id.userphone);
            textViewWebsite = mView.findViewById(R.id.userwebsite);
            textViewCompanyName = mView.findViewById(R.id.usercompanyname);
            textViewCompanyPhrase = mView.findViewById(R.id.usercompanyphrase);
        }
    }
}
