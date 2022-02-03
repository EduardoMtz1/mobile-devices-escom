package com.example.myapplication;

import android.content.*;
import android.graphics.*;
import android.view.View;
public class Lienzo extends View {
    Paint p;
    Path r;
    int x, y, x0, y0;

    public Lienzo(Context c) {
        super(c);
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
        p = new Paint();
        r = new Path();
        x = c.getWidth();
        x0 = x / 2; // También: getMeasuredWidth(), o getRight(), x=480
        y = c.getHeight();
        y0 = y / 2; // También: getMeasuredHeight(), o getBottom(), y=762
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(20);
        c.drawText("0,0", x0 + 5, y0 + 20, p);
        p.setColor(Color.rgb(0, 0, 255)); // Ejes azules
        c.drawLine(x0, 0, x0, y, p);
        c.drawLine(0, y0, x, y0, p);
        p.setColor(Color.BLUE);
        c.drawText("senA", 20, 20, p);
        p.setColor(Color.RED);
        c.drawText("cosA", 20, 50, p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        p.setAntiAlias(true);
        r = new Path();
        x = getMeasuredWidth();
        r.moveTo(0, 0);
        p.setColor(Color.BLUE);
        for (int i = 1; i < x; i++) r.lineTo(i, (float) Math.sin(i / 10f) * (-70f));
        r.offset(-10, y0);      c.drawPath(r, p);
        r = new Path();
        r.moveTo(0, 0);       p.setColor(Color.RED);
        for (int i = 1; i < x; i++) r.lineTo(i, (float) Math.cos(i / 15f) * (-120f));
        r.offset(-10, y0);
        c.drawPath(r, p);
        c.drawText("Amplitud Seno: 140", 540, 500, p);
        c.drawText("Amplitud Coseno: 240", 540, 550, p);
        c.drawText("Periodo Seno: 10", 540, 600, p);
        c.drawText("Periodo Coseno: 15", 540, 650, p);
    }
}