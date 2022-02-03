package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;

public class Lienzo extends View {
    String image_data;
    Bitmap bitmap;
    Paint p;
    public Lienzo(Context context, String s) {
        super(context);
        p = new Paint();
        p.setAntiAlias(true);
        p.setFilterBitmap(true);
        p.setDither(true);
        image_data = s;
        Log.d("Cadena Lienzo: ", image_data);
        byte[] b = Base64.decode(image_data, Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, p);
        //imageConvertResult.setImageBitmap(bitmap);
    }
}
