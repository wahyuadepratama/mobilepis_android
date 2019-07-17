package com.timerlearning.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.timerlearning.AppDatabase;
import com.timerlearning.R;

import java.util.Objects;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.w("NEW_TOKEN: ",s);
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("Tag", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("Tag", "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("Tag", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        String body = null;
        String title = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            body = Objects.requireNonNull(remoteMessage.getNotification()).getBody();
            title = Objects.requireNonNull(remoteMessage.getNotification()).getTitle();
        }
        showNotification(title, body);
    }

    private void showNotification(String messageTitle, String messageBody) {

        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "AlarmManager channel";
        NotificationManager notificationManagerCompat = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setColor(ContextCompat.getColor(this, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        int id = (int) System.currentTimeMillis();
        Notification notification = builder.build();
        if (notificationManagerCompat != null) {
            AppDatabase settingDb = Room.databaseBuilder(this, AppDatabase.class, "setting.db")
                    .allowMainThreadQueries()
                    .build();
            Boolean currentSetting = settingDb.tbSettingDao().getNotification();

            if (currentSetting){
                notificationManagerCompat.notify(id, notification);
            }
        }

//        Intent intent = new Intent(this, HistoryActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.img_logo)
//                .setContentTitle(messageTitle)
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(messageBody))
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("Channel_1",
//                    "Notification",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            channel.enableVibration(true);
//            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
//            notificationBuilder.setChannelId("Channel_1");
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(channel);
//            }
//        }
//
//        int id = (int) System.currentTimeMillis();
//        AppDatabase settingDb = Room.databaseBuilder(this, AppDatabase.class, "setting.db")
//                .allowMainThreadQueries()
//                .build();
//        Boolean currentSetting = settingDb.tbSettingDao().getNotification();
//
//        if (currentSetting){
//            notificationManager.notify(id, notificationBuilder.build());
//        }
    }
}
