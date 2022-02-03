package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class CirCiclo extends View {
    Paint p;
    int x,y;
    String color;
    int tam, in;
    public CirCiclo(Context c, String col, int tm, int ina) {
        super(c);
        color = col;
        tam = tm;
        in = ina>10?10:ina;
    }
    protected void onDraw(Canvas c){
        super.onDraw(c);
        p = new Paint();
        x = c.getWidth();
        y = c.getHeight();
        p.setColor(Color.WHITE);
        c.drawPaint(p);
        p.setColor(Color.parseColor(color));
        p.setStyle(Paint.Style.STROKE);
        for(int i = 0;i<in;i++){
            dibuja(c);
            tam = (int)(tam/Math.sqrt(2.0));
        }

    }

    public void dibuja(Canvas c){
        c.drawRect((x/2)-(tam/2),(y/2)-(tam/2),(x/2)+(tam/2),(y/2)+(tam/2),p);
        c.drawCircle(x/2,y/2,tam/2,p);
    }
}
