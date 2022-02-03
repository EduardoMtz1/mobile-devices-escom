package com.example.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

// NOTA: Agregar las bibliotecas de clases necesarias.
public class MainActivity extends Activity { // Abre un archivo almacenado
    TextView tv;
    String s;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.xtv);
        tv.append("\nAbriendo: res/raw/misdatos.txt"); // NOTA: Este es su archivo.
        is = getResources().openRawResource(R.raw.misdatos);
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr, 8192);
        try {
            while (null != (s = br.readLine()))
                tv.append("\n" + s);
            is.close();
            isr.close();
            br.close();
        } catch (Exception e) {
            tv.append("\n " + e);
        }
        tv.append("\nEnd of file.");
    }
}