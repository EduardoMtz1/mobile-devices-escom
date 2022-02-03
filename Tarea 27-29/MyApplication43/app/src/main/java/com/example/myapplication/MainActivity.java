package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends Activity {
    Button sndr;
    Button slct;
    Uri uri;
    Uri un;
    File [] snd;
    private int VALOR_RETORNO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sndr = (Button) findViewById(R.id.btn1);
        slct = (Button) findViewById(R.id.btn2);

        sndr.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                try {
                    File send = new File("info.zip");
                    Zipper z = new Zipper(send);
                    File src = new File(uri.getPath());
                    snd[0] = src;
                    z.zip(snd);
                    un = Uri.fromFile(send);
                    Toast.makeText(getApplicationContext(),"Archivo comprimido",2);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
                compartir.setType("application/zip, application/octet-stream, application/x-zip-compressed, multipart/x-zip");
                compartir.putExtra(Intent.EXTRA_STREAM, un);
                Intent shareIntent = Intent.createChooser(compartir, null);
                startActivity(shareIntent);

            }
        });

        slct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, "Choose File"), VALOR_RETORNO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            //Cancelado por el usuario
        }
        if ((resultCode == RESULT_OK) && (requestCode == VALOR_RETORNO)) {
            //Procesar el resultado
            uri = data.getData(); //obtener el uri content
        }
    }
}