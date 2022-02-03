package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;

import static java.lang.Math.sqrt;

public class SegundaActivity extends Activity {
    EditText jet1, jet2;
    Bundle bdl;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jet1 = (EditText) findViewById(R.id.xet1);
        jet2 = (EditText) findViewById(R.id.xet2);
        bdl = getIntent().getExtras();
        int x = Integer.parseInt(bdl.getString("A"));
        int y = Integer.parseInt(bdl.getString("B"));
        int z = Integer.parseInt(bdl.getString("C"));
        raices(x,y,z,jet1,jet2);
        //jet.append("Hola " + bdl.getString("NOMBRE"));
    }

    public void raices (int a, int b, int c, EditText jet1, EditText jet2){
        int part = ((int)Math.pow(b,2)-(4*a*c));
        double raiz1, raiz2;
        if(part < 0){
            part = part * (-1);
            raiz1 = (-b)/(2*a);
            raiz2 = sqrt(part)/(2*a);
            jet1.append(String.valueOf(raiz1)+"+"+String.valueOf(raiz2)+"i");
            jet2.append(String.valueOf(raiz1)+"-"+String.valueOf(raiz2)+"i");
        }else{
            raiz1 = ((-b)+sqrt(part))/(2*a);
            raiz2 = ((-b)-sqrt(part))/(2*a);
            jet1.append(String.valueOf(raiz1));
            jet2.append(String.valueOf(raiz2));
        }

    }
}
