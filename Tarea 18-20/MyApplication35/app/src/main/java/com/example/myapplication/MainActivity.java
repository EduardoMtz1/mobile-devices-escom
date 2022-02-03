package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences; import android.os.*;
import android.app.*;
import android.view.View;
import android.widget.*;
public class MainActivity extends Activity {
    SharedPreferences sp;
    EditText jetn, jeta;
    String n, a;
    Button jbna;
    Intent itn;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jetn = (EditText) findViewById(R.id.xetn);
        jeta = (EditText) findViewById(R.id.xeta);
        sp = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        n = sp.getString("nombre", "Nombre");
        a = sp.getString("apellido", "Apellido");
        jetn.setText(n);
        jeta.setText(a);
        jbna = (Button) findViewById(R.id.xbna);
        jbna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                Bundle bdl = new Bundle();
                bdl.putString("nombre",n);
                bdl.putString("apellido", a);
                itn.putExtras(bdl);
                startActivity(itn);
            }
        });

    }

    protected void onPause() {
        super.onPause();
        n = jetn.getText().toString();
        a = jeta.getText().toString();
        SharedPreferences.Editor miEditor = sp.edit();
        miEditor.putString("nombre", n);
        miEditor.putString("apellido", a);
        miEditor.commit();
        Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_LONG).show();
    }
}