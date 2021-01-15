package com.example.smart_home;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<com.example.smart_home.AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<com.example.smart_home.Album> albumList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public Switch s ;
        public View view;

        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.title);
           // count = (TextView) view.findViewById(R.id.count);
          //  thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            s=(Switch)view.findViewById(R.id.switch1);

       //     overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumsAdapter(Context mContext, List<com.example.smart_home.Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        com.example.smart_home.Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.s.setText(album.getName());



      //  holder.count.setText(album.getNumOfSongs() + " songs");

        // loading album cover using Glide library
       // Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);




        holder.s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   Toast.makeText(mContext.getApplicationContext(),holder.s.getTextOff(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    /**
     * Showing popup menu when tapping on 3 dots
     */


    /**
     * Click listener for popup menu items
     */






    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
