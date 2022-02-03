package com.example.examen;

import android.os.Bundle; import android.app.Activity; import android.view.View; import android.view.View.*; import android.content.Intent; import android.widget.*;
public class MainActivity extends Activity {
    EditText xte, yte, zte;
    Button jbn;
    Bundle bdl;
    Intent itn;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        xte = (EditText) findViewById(R.id.xet);
        yte = (EditText) findViewById(R.id.yet);
        zte = (EditText) findViewById(R.id.zet);
        jbn = (Button) findViewById(R.id.xbn);
        jbn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                bdl = new Bundle();
                bdl.putString("X", xte.getText().toString());
                bdl.putString("Y", yte.getText().toString());
                bdl.putString("Z", zte.getText().toString());
                itn.putExtras(bdl);
                startActivity(itn);
            }
        });

    }
}