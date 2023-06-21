package com.example.myapplication.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.HandlingNotificationActivity;
import com.example.myapplication.R;
import com.example.myapplication.common.IntentKeys;

public class MyUnboundService extends Service {
    public MyUnboundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        String username = intent.getStringExtra(IntentKeys.username);
        sendNotification(username);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendNotification(String username) {
        String channelId = "001";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_action_favourite)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("Demo Noti")
                .setContentText("Hello " + username);
        Intent intent = new Intent(this, HandlingNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 4, intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "demo chanel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(2, builder.build());
    }
}