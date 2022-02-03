package com.example.myapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {
    Button jbn1, jbn2;
    MediaPlayer mp;
    VideoView vvw;
    Uri uri;
    MediaController mcr;
    private int VALOR_RETORNO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jbn1 = (Button)findViewById(R.id.xbn1);
        jbn2 = (Button)findViewById(R.id.xbn2);
        jbn1.setOnClickListener(this);
        jbn2.setOnClickListener(this);

    }
    public void onClick(View v) {

        if (v.getId() == R.id.xbn1) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(Intent.createChooser(intent, "Choose File"), VALOR_RETORNO);
        }
        if (v.getId() == R.id.xbn2) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/mp4");
            startActivityForResult(Intent.createChooser(intent, "Choose File"), VALOR_RETORNO);
        }
    }
    void reproducir(){
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(getApplicationContext(), uri);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();

    }
    public void onPause(){ super.onPause(); if(mp != null)
        mp.release();
    }

    void repvideo(){
        vvw = (VideoView) findViewById(R.id.xvv1);
        mcr = new MediaController(this);
        vvw.setMediaController(mcr);
        vvw.setVideoURI(uri);
        vvw.start();
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
            String mimeType = getContentResolver().getType(uri);
            if(mimeType.equals("video/mp4")){
                repvideo();
            }else{
                reproducir();

            }


        }
    }




}