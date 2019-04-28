package com.media.service;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;

import com.media.service.mediaapi.MediaSessionManager;
import com.media.service.utils.MediaMetaData;
import com.media.service.utils.PackageValidator;
import com.media.service.utils.ServiceLifeCycle;
import com.mohan.mediaapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MediaService extends MediaBrowserServiceCompat implements ServiceLifeCycle {
    private static final int SERVICE_RUNNING_NOTIFICATION_ID = 10000;
    private static final String CHANNEL_ID = "MediaService";
    private static final String CHANNEL_NAME = "Audio";
    private static final String PACKAGAGE_NAME = "com.media.client";
    private static final String ROOT_ID = "root_id";
    private static final String EMPTY_ROOT = "empty_root_id";
    private MediaSessionCompat mMediaSession;
    private MediaSessionManager mMediaSessionManager;

    private PackageValidator mPackageValidator;
    private MediaMetaData mMediaMetaData;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaMetaData = new MediaMetaData();
        mPackageValidator = new PackageValidator(this);
        mMediaSession = new MediaSessionCompat(getApplicationContext(), "MediaService");

        mMediaSessionManager = new MediaSessionManager(mMediaSession);

        setSessionToken(mMediaSession.getSessionToken());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @androidx.annotation.Nullable
    @Nullable
    @Override
    public BrowserRoot onGetRoot(@androidx.annotation.NonNull @NonNull String clientPackageName,
            int clientUid, @androidx.annotation.Nullable @Nullable Bundle rootHints) {
        if (mPackageValidator.isCallerAllowed(getApplicationContext(), clientPackageName,
                clientUid)) {
            return new BrowserRoot(ROOT_ID, null);
        } else {
            return new BrowserRoot(EMPTY_ROOT, null);
        }
    }

    @Override
    public void onLoadChildren(@NonNull String parentMediaId,
            @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {

        if (TextUtils.equals(EMPTY_ROOT, parentMediaId)) {
            result.sendResult(new ArrayList<MediaBrowserCompat.MediaItem>());
            return;
        }

        List<MediaBrowserCompat.MediaItem> mMediaItemList = mMediaMetaData.createMediaItem(
                getApplicationContext());

        if (ROOT_ID.equals(parentMediaId)) {
            //TODO:need to implement the sonǵ list
        } else {
            //TODO:need to implement the sonǵ list
        }
        result.sendResult(mMediaItemList);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void startMediaService() {
        startService(new Intent(this, MediaService.class));
    }

    @Override
    public void stopMediaService() {
        stopSelf();
    }

    public Notification buildNotification() {
        // Given a media session and its context (usually the component containing the session)
// Create a NotificationCompat.Builder

// Get the session's metadata
        MediaControllerCompat controller = mMediaSession.getController();
        MediaMetadataCompat mediaMetadata = controller.getMetadata();
        MediaDescriptionCompat description = mediaMetadata.getDescription();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                CHANNEL_ID);

        builder
                // Add the metadata for the currently playing track
                .setContentTitle(description.getTitle())
                .setContentText(description.getSubtitle())
                .setSubText(description.getDescription())
                .setLargeIcon(description.getIconBitmap())

                // Enable launching the player by clicking the notification
                .setContentIntent(controller.getSessionActivity())

                // Stop the service when the notification is swiped away
                .setDeleteIntent(
                        MediaButtonReceiver.buildMediaButtonPendingIntent(getApplicationContext(),
                                PlaybackStateCompat.ACTION_STOP))

                // Make the transport controls visible on the lockscreen
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                // Add an app icon and set its accent color
                // Be careful about the color
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))

                // Add a pause button
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_pause_circle_outline_black_24dp,
                        getString(R.string.label_pause),
                        MediaButtonReceiver.buildMediaButtonPendingIntent(getApplicationContext(),
                                PlaybackStateCompat.ACTION_PLAY_PAUSE)));


// Display the notification and place the service in the foreground
        startForeground(SERVICE_RUNNING_NOTIFICATION_ID, builder.build());
        return builder.build();
    }
}
