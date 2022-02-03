package com.example.examen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SegundaActivity extends Activity {
    Bundle bdl;
    double x[] = new double[3];
    double y[] = new double[3];
    double z[] = new double[3];
    String[] Argsx = {};
    String[] Argsy = {};
    String[] Argsz = {};
    @Override
    protected void onCreate( Bundle b) {
        super.onCreate(b);
        bdl = getIntent().getExtras();
        Argsx = bdl.getString("X").split(",");
        Argsy = bdl.getString("Y").split(",");
        Argsz = bdl.getString("Z").split(",");

        for(int i = 0; i<Argsx.length;i++) {
            String p = Argsx[i];
            x[i] = Double.parseDouble(p);
        }
        for(int i = 0; i<Argsy.length;i++){
            y[i] = Double.parseDouble(Argsy[i]);
        }
        for(int i = 0; i<Argsz.length;i++){
            z[i] = Double.parseDouble(Argsz[i]);
        }
        Lienzo l = new Lienzo(getApplicationContext(),x,y,z);
        setContentView(l);
    }
}
