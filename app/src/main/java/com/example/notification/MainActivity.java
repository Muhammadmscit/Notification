package com.example.notification;

import static com.example.notification.App.CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager=NotificationManagerCompat.from(this);



    }

    public void showNotification(View view) {
        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Title")
                .setContentText("This is Default Notification ").build();

        notificationManager.notify(1,notification);
    }

    public void custom_Notification(View view) {
        RemoteViews collasped_view=new RemoteViews(getPackageName(),R.layout.notification_collapsed);
        RemoteViews expended_view=new RemoteViews(getPackageName(),R.layout.notification_expended);

        Intent intent=new Intent(this,Notification_Recever.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        expended_view.setOnClickPendingIntent(R.id.image_view_expended,pendingIntent);

        collasped_view.setTextViewText(R.id.text_view_collapsed_1,"Hello World!");


        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setCustomContentView(collasped_view)
                .setCustomBigContentView(expended_view)

                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())

                .build();

        notificationManager.notify(1,notification);
    }
}