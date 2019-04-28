package com.mohan.mediaapplication.adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class SongDetails implements Parcelable {

    public static final Creator<SongDetails> CREATOR = new Creator<SongDetails>() {
        @Override
        public SongDetails createFromParcel(Parcel in) {
            return new SongDetails(in);
        }

        @Override
        public SongDetails[] newArray(int size) {
            return new SongDetails[size];
        }
    };
    private String mMediaId;
    private String mMediaUri;
    private String mMediaSongName;
    private String mMediaLogo;
    private String mMediaArtist;

    protected SongDetails(Parcel in) {
    }

    public static Creator<SongDetails> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public String getMediaId() {
        return mMediaId;
    }

    public String getMediaUri() {
        return mMediaUri;
    }

    public String getMediaSongName() {
        return mMediaSongName;
    }

    public String getMediaLogo() {
        return mMediaLogo;
    }

    public String getMediaArtist() {
        return mMediaArtist;
    }
}
