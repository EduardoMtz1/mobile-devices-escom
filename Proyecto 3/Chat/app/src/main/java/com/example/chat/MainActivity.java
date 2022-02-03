package com.example.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText user;
    Button jbn1;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText)findViewById(R.id.xetI);
        jbn1 = (Button)findViewById(R.id.xbnA);
        jbn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = new Bundle();
                String usr = user.getText().toString();
                Intent i = new Intent(MainActivity.this, Chat.class);
                b.putString("user", usr);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}