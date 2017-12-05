package com.example.akibtalcompany.remindme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.example.akibtalcompany.remindme.AppHelper.NOTIFICATION_ID;

public class NotiBuilder extends BroadcastReceiver {

    public static final String NOTINAME = "NOTINAME";

    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getStringExtra(NOTINAME);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeatingIntent = new Intent(context, MainActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_ID,
                repeatingIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(name)
                .setContentText("REMINDER")
                .setAutoCancel(true);

        if (nm != null) {
            nm.notify(NOTIFICATION_ID, builder.build());
        }
    }
}
