package com.example.myapplication;

import android.app.Notification;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private NotificationUtils nu;
    EditText jetTit, jetAut;
    Button jbnEnv;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        nu = new NotificationUtils(this);
        jetTit = (EditText) findViewById(R.id.xetTit);
        jetAut = (EditText) findViewById(R.id.xetAut);
        jbnEnv = (Button) findViewById(R.id.xbnEnv);
        jbnEnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = jetTit.getText().toString();
                String autor = jetAut.getText().toString();
                if (!TextUtils.isEmpty(titulo) && !TextUtils.isEmpty(autor)) {
                    Notification.Builder nb = nu.
                            getAndroidChannelNotification(titulo, "Por: " + autor);
                    nu.getManager().notify(101, nb.build());
                }
            }
        });
    }
}