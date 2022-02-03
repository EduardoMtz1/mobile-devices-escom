package com.example.myapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {
    SharedPreferences sp;
    Button jbn1, jbn2;
    Uri uri;
    Bitmap image;
    Bundle bdl;
    Intent itn;
    String fin;
    private int VALOR_RETORNO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jbn1 = (Button)findViewById(R.id.xbn1);
        jbn2 = (Button)findViewById(R.id.xbn2);
        sp = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        jbn1.setOnClickListener(this);
        jbn2.setOnClickListener(this);

    }
    public void onClick(View v) {
        if (v.getId() == R.id.xbn1) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Choose File"), VALOR_RETORNO);

        }
        if(v.getId() == R.id.xbn2){
            itn = new Intent(MainActivity.this, SegundaActivity.class);
            startActivity(itn);
        }

    }

    public void guardaPreferencias(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        //textEncode.setText(encodedImage);
        SharedPreferences.Editor edit=sp.edit();
        edit.putString("image_data",encodedImage);
        edit.commit();
        fin = encodedImage;
        Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_LONG).show();
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
            if(mimeType.equals("image/jpeg")){
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    guardaPreferencias();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}