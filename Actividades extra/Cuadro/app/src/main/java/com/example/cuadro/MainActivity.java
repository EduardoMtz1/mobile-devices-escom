package com.example.cuadro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText iteraciones;
    Button jbn1;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iteraciones = (EditText)findViewById(R.id.xetI);
        jbn1 = (Button)findViewById(R.id.xbnA);
        jbn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = new Bundle();
                String it = iteraciones.getText().toString();
                Intent i = new Intent(MainActivity.this, Cuadro.class);
                b.putInt("i",Integer.parseInt(it));
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}