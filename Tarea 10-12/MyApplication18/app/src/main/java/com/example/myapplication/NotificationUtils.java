package com.example.myapplication;

import android.app.*;
import android.content.*;
import android.graphics.Color;
public class NotificationUtils extends ContextWrapper {
    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.example.myapplication.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";

    public NotificationUtils(Context c) {
        super(c);
        createChannels();
    }

    public void createChannels() {
// Se crea el canal android
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
// Establece si las notificaciones publicadas en este canal deben mostrar luces de notificación
        androidChannel.enableLights(true);
// Establece si la notificación publicada en este canal debe vibrar.
        androidChannel.enableVibration(true);
// Establece el color de la luz de notificación para las notificaciones publicadas en este canal.
        androidChannel.setLightColor(Color.GREEN);
// Establece si las notificaciones publicadas en este canal aparecen en la pantalla de bloqueo o no
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(androidChannel);
    }

    public Notification.Builder getAndroidChannelNotification(String titulo, String texto) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
}