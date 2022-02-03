package com.example.botones;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener; //1

public class MainActivity extends Activity implements OnClickListener{ //2
    EditText jet;
    Button jbn;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jet = (EditText) findViewById(R.id.xet);
        jbn = (Button) findViewById(R.id.xbn);  jbn.setOnClickListener(this); //3
    }

    public void  onClick(View v){ //4
        jet.append("Datos = " + x++); //5
    }
}