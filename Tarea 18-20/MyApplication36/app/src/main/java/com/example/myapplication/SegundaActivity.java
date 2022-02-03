package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


public class SegundaActivity extends Activity {
    SharedPreferences sp;
    public void onCreate(Bundle b){
        super.onCreate(b);
        sp = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String image_data =sp.getString("image_data", null);
        Log.e("Cadena:", image_data);
        Lienzo l = new Lienzo(getApplicationContext(), image_data);
        setContentView(l);
    }

}
