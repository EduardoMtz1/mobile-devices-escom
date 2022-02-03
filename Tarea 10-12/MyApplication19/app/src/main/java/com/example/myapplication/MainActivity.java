package com.example.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private NotificationUtils nu;
    Button jbnNot;
    int id = 1;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        nu = new NotificationUtils(this);
        jbnNot = (Button) findViewById(R.id.xbn);
        jbnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder nb = nu.
                        getAndroidChannelNotification();
                nu.getManager().notify(101, nb.build());
            }
        });
    }

}