package com.mohan.mediaapplication.adapter;

import android.content.Context;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohan.mediaapplication.R;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MediaBrowserCompat.MediaItem> mSongDetails;

    public SongListAdapter(Context context, List<MediaBrowserCompat.MediaItem> mChildrenList) {
        this.mContext = context;
        this.mSongDetails = mChildrenList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.song_list_view, null);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        UserViewHolder holder = (UserViewHolder) viewHolder;
        holder.songLogo.setImageURI(mSongDetails.get(i).getDescription().getIconUri());
        holder.songTitle.setText(mSongDetails.get(i).getDescription().getTitle());
        holder.artistName.setText(mSongDetails.get(i).getDescription().getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mSongDetails.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView songLogo;
        private TextView songTitle;
        private TextView artistName;

        public UserViewHolder(View itemView) {
            super(itemView);
            songLogo = itemView.findViewById(R.id.song_logo);
            songTitle = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
        }
    }
}