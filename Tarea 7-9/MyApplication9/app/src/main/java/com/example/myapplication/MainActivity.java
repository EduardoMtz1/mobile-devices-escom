package com.example.myapplication;

import android.os.Bundle; import android.app.Activity; import android.view.View; import android.view.View.*; import android.content.Intent; import android.widget.*;
public class MainActivity extends Activity {
    EditText jet;
    Button jbn;
    Bundle bdl;
    Intent itn;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbn = (Button) findViewById(R.id.xbn);
        jbn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn = new Intent(MainActivity.this, Lista.class);
                /*bdl = new Bundle();
                bdl.putString("NOMBRE", jet.getText().toString());
                itn.putExtras(bdl);*/
                startActivity(itn);
            }
        });
    }
}