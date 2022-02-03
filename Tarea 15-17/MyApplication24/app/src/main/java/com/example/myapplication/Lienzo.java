package com.example.myapplication;

import android.content.*; import android.graphics.*; import android.view.View;
public class Lienzo extends View {
    Paint p;
    int x, y;

    public Lienzo(Context c) {
        super(c);
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        x = c.getWidth(); // También: getMeasuredWidth() o getRight(), x=480
        y = c.getHeight(); // También: getMeasuredHeight() o getBottom(), y=762
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(20);
        c.drawText("x0=" + x / 2 + ", y0=" + y / 2, x / 2 + 20, y / 2 - 20, p);
        p.setColor(Color.rgb(0, 0, 255)); // Ejes azules
        c.drawLine(x/2, 0, x/2, y, p);
        c.drawLine(0, y / 2, x, y / 2, p);
        p.setTextAlign(Paint.Align.LEFT);
        p.setTypeface(Typeface.DEFAULT);
        c.drawText("Eje X", x-5, y/2-10, p);
        p.setTextAlign(Paint.Align.LEFT);
        p.setTypeface(Typeface.SANS_SERIF);
        c.drawText("Eje Y", x/2+30, 20, p);
        p.setColor(Color.argb(100, 200, 100, 100));
        //c.drawCircle(x/4, (3*y)/4, 100, p);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        RectF oval1 = new RectF(600, 100, getWidth()-80, 700);
        c.drawOval(oval1, paint);

        oval1 = new RectF(80, 100, 280, 700);
        c.drawRect(oval1, paint);

        oval1 = new RectF(80, 1000, 280, 1600);
        c.drawRoundRect(oval1, 20, 20, paint);

        oval1 = new RectF(600, 1000, getWidth()-80, 1600);
        c.drawArc (oval1, 90, 180, false, p);
    }
}
