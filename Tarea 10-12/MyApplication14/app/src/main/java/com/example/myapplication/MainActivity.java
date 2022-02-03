package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity { // ServiceTimerActivity
    private TextView jtv;
    private Button jbn;
    private boolean pausa = false;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtvT);
        jbn = (Button) findViewById(R.id.xbnI);
        jbn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initCrono();
            }
        });
        Button stopButton = (Button) findViewById(R.id.xbnT);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopCrono();
            }
        });
        Button pauseButton = (Button) findViewById(R.id.xbnP);
        pauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                pausa = true;
            }
        });
        Button contiButton = (Button) findViewById(R.id.xbnC);
        contiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pausa = false;
            }
        });
        MiCrono.setUpdateListener(this);
    }

    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }

    private void initCrono() {
        pausa = false;
        Intent in = new Intent(this, MiCrono.class);
        startService(in);

    }

    private void stopCrono() {
        Intent in = new Intent(this, MiCrono.class);
        stopService(in);
    }



    public void refreshCrono(double t) {
        jtv.setText(String.format("%.2f", t) + " segs");
    }

    public boolean estado(){
        return pausa;
    }
}