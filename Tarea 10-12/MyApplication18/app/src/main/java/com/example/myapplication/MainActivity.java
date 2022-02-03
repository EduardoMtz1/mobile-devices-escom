package com.example.myapplication;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private NotificationUtils nu;
    int i = 0;
    TextView jtv;
    Button jbnNot;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        nu = new NotificationUtils(this);
        jtv =(TextView) findViewById(R.id.xtv);
        jtv.setText("Cuenta i = " + i);
        jbnNot = (Button) findViewById(R.id.xbnN);
        jbnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                jtv.setText("Cuenta i = " + i);
                String titulo = "Alerta de Notificacion";
                String valor = "Uso de una notificacion. I = " + i;
                Notification.Builder nb = nu.
                        getAndroidChannelNotification(titulo, valor);
                nu.getManager().notify(101, nb.build());
            }
        });
    }
}