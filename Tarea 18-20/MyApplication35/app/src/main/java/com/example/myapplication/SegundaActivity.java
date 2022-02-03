package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends Activity {
    TextView nombre, apellido;
    Bundle bdl;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        nombre = (TextView) findViewById(R.id.xtv1);
        apellido = (TextView) findViewById(R.id.xtv2);
        bdl = getIntent().getExtras();
        nombre.append(bdl.getString("nombre"));
        apellido.append(bdl.getString("apellido"));
    }
}
