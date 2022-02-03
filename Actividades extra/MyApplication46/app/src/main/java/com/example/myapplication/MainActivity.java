package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{
    EditText jt1, jt2, jt3;
    Button bn1, bn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jt1 = (EditText) findViewById(R.id.xt1);
        jt2 = (EditText) findViewById(R.id.xt2);
        jt2.setFilters(new InputFilter[]{
                new InputFilterMinMax(0,1080)
        });
        jt3 = (EditText) findViewById(R.id.xt3);
        jt3.setFilters(new InputFilter[]{
                new InputFilterMinMax(1,100000)
        });
        bn1 = (Button) findViewById(R.id.xbn1);
        bn1.setOnClickListener(this);
        bn2 = (Button) findViewById(R.id.xbn2);
        bn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.xbn1){
            Intent in = new Intent(MainActivity.this, Lienzo.class);
            Bundle bn = new Bundle();
            bn.putString("color",jt1.getText().toString());
            bn.putInt("tam",Integer.parseInt(jt2.getText().toString()));
            bn.putInt("it",Integer.parseInt(jt3.getText().toString()));
            in.putExtras(bn);
            startActivity(in);
        }else if(v.getId() == R.id.xbn2){
            Intent in = new Intent(MainActivity.this, Circulo.class);
            Bundle bn = new Bundle();
            bn.putString("color",jt1.getText().toString());
            bn.putInt("tam",Integer.parseInt(jt2.getText().toString()));
            bn.putInt("it",Integer.parseInt(jt3.getText().toString()));
            in.putExtras(bn);
            startActivity(in);
        }
        //To do
    }
}