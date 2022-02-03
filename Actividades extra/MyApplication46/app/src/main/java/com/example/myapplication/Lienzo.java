package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class Lienzo extends Activity {
    String color;
    int tam;
    int in;
    Bundle bn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bn = getIntent().getExtras();
        color = bn.getString("color");
        tam = bn.getInt("tam");
        in = bn.getInt("it");
        Cuadro c = new Cuadro(getApplicationContext(),color, tam, in);
        setContentView(c);
    }
}
