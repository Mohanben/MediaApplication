package com.media.service.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;

import com.mohan.mediaapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MediaMetaData {
    private ArrayList<MediaBrowserCompat.MediaItem> mMediaItemList;

    public List<MediaBrowserCompat.MediaItem> createMediaItem(
            Context applicationContext) {
        String songPathPrefix = "android.resource://" + applicationContext.getPackageName();
        if (mMediaItemList == null) {
            mMediaItemList = new ArrayList<>();

            Bitmap station1Icon = BitmapFactory.decodeResource(
                    applicationContext.getResources(), R.drawable.ic_menu_camera);
            Bitmap station2Icon = BitmapFactory.decodeResource(
                    applicationContext.getResources(), R.drawable.ic_menu_manage);
            Bitmap station3Icon = BitmapFactory.decodeResource(
                    applicationContext.getResources(), R.drawable.ic_menu_slideshow);

            mMediaItemList.add(new MediaBrowserCompat.MediaItem(
                    new MediaDescriptionCompat.Builder()
                            .setMediaId("a")
                            .setTitle("aaromale")
                            .setSubtitle("AR Rahman")
                            .setMediaUri(Uri.parse((songPathPrefix + "/raw/aaromale")))

                            .setIconBitmap(station1Icon)
                            .build(),
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));

            mMediaItemList.add(new MediaBrowserCompat.MediaItem(
                    new MediaDescriptionCompat.Builder()
                            .setMediaId("b")
                            .setTitle("Hosanna")
                            .setSubtitle("AR Rahman")
                            .setMediaUri(Uri.parse((songPathPrefix + "/raw/hosanna")))
                            .setIconBitmap(station2Icon)
                            .build(),
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));

            mMediaItemList.add(new MediaBrowserCompat.MediaItem(
                    new MediaDescriptionCompat.Builder()
                            .setMediaId("c")
                            .setTitle("Kannukul Kannai")
                            .setSubtitle("AR Rahman")
                            .setMediaUri(Uri.parse((songPathPrefix + "/raw/kannukul_kannai")))
                            .setIconBitmap(station3Icon)
                            .build(),
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));

            mMediaItemList.add(new MediaBrowserCompat.MediaItem(
                    new MediaDescriptionCompat.Builder()
                            .setMediaId("d")
                            .setTitle("Vinnathaandi Varuvaayaa")
                            .setSubtitle("AR Rahman")
                            .setMediaUri(
                                    Uri.parse((songPathPrefix + "/raw/vinnathaandi_varuvaayaa")))
                            .setIconBitmap(station3Icon)
                            .build(),
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));

            mMediaItemList.add(new MediaBrowserCompat.MediaItem(
                    new MediaDescriptionCompat.Builder()
                            .setMediaId("e")
                            .setTitle("Omana Penne")
                            .setSubtitle("AR Rahman")
                            .setMediaUri(Uri.parse((songPathPrefix + "/raw/omana_penne")))
                            .setIconBitmap(station3Icon)
                            .build(),
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));

            mMediaItemList.add(new MediaBrowserCompat.MediaItem(
                    new MediaDescriptionCompat.Builder()
                            .setMediaId("f")
                            .setTitle("Mannipaaya")
                            .setSubtitle("AR Rahman")
                            .setMediaUri(Uri.parse((songPathPrefix + "/raw/mannipaaya")))
                            .setIconBitmap(station3Icon)
                            .build(),
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));
        }

        return mMediaItemList;
    }

}
