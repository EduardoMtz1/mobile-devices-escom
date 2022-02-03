package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.Random;

public class Cuadro extends View {
    Paint p;
    int x,y;
    String color;
    int tam, in;
    int[][] vertices = new int[3][2];
    public Cuadro(Context c, String col, int tm, int ina) {
        super(c);
        color = col;
        tam = tm;
        in = ina;
    }
    protected void onDraw(Canvas c){
        super.onDraw(c);
        p = new Paint();
        x = c.getWidth();
        y = c.getHeight();
        p.setColor(Color.WHITE);
        c.drawPaint(p);
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.RIGHT);
        p.setTextSize(35);
        c.drawText("Color: " + color,x/2, y/10,p);
        c.drawText("Tamaño: " + tam,x/2, (y/10)+100,p);
        c.drawText("Iteraciones: " + in,x/2, (y/10)+200,p);
        c.drawCircle(x/2, y/3,10,p);
        vertices[0][0] = x/2;
        vertices[0][1] = y/3;
        int cord_x1 = (x/2)-(tam/2);
        int cord_x2 = (x/2)+(tam/2);
        int cord_y = (int) ((y/3)+ Math.sqrt((tam*tam) - ((tam/2)*(tam/2))));
        c.drawCircle(cord_x1, cord_y,10,p);
        vertices[1][0] = cord_x1;
        vertices[1][1] = cord_y;
        c.drawCircle(cord_x2, cord_y,10,p);
        vertices[2][0] = cord_x2;
        vertices[2][1] = cord_y;
        fractal(c);
    }

    public void fractal(Canvas c){
        int x0, y0, xm, ym;
        p.setColor(Color.parseColor(color));
        x0 = x/3;
        y0 = y/3;
        c.drawCircle(x0, y0,5,p);
        for(int i = 0; i< in; i++){
            int rand = new Random().nextInt(3);
            xm = (x0 + vertices[rand][0])/2;
            ym = (y0 + vertices[rand][1])/2;
            c.drawCircle(xm, ym,1,p);
            x0 = xm;
            y0 = ym;
        }
    }
}
