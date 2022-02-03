package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.*;

import com.example.myapplication.Obj;

public class Perspectiva{
    int centerX, centerY, maxX, maxY, minMaxXY;
    Obj obj = new Obj();
    Canvas c;
    Paint p;

    public Perspectiva(Canvas can, Paint pa){
        c = can;
        p = pa;
        centerX = c.getWidth()/2;
        centerY = c.getHeight()/2;
        maxX = c.getWidth();
        maxY = c.getHeight();
    }

    int iX(float x){
        return Math.round(centerX + x);
    }

    int iY(float y){
        return Math.round(centerY - y);
    }

    void line(int i, int j){
        Point2D a = obj.vScr[i], b = obj.vScr[j];
        p.setColor(Color.BLACK);
        Log.e("Pinta","A pintar linea:" + i + "," + j);
        c.drawLine(iX(a.x), iY(a.y), iX(b.x), iY(b.y),p);
        return;
    }

    public void pinta(){
        minMaxXY=Math.min(maxX, maxY);
        centerX = maxX/4;
        centerY = maxY/2;
        obj.d = obj.rho*minMaxXY/obj.objSize;
        obj.eyeAndScreen();
        line(0, 1); line(1, 2); line( 2, 3); line(3, 0); // aristas horizontales inferiores
        line( 4, 5); line(5, 6); line(6, 7); line( 7, 4); // aristas horizontales superiores
        line( 0, 4); line( 1, 5); line(2, 6); line(3, 7); // aristas verticales
        centerX = 3 * (maxX/4);
        centerY = maxY/2;
        obj.d = obj.rho*minMaxXY/obj.objSize;
        obj.eyeAndScreen();
        line(0, 1); line(1, 2); line( 2, 3); line(3, 0); // aristas horizontales inferiores
        line( 4, 5); line(5, 6); line(6, 7); line( 7, 4); // aristas horizontales superiores
        line( 0, 4); line( 1, 5); line(2, 6); line(3, 7); // aristas verticales
        return;
    }

    public void movimiento(int x, int y){
        obj.theta = maxX/x;
        obj.phi = maxY/y;
        obj.rho = (obj.phi/obj.theta)*maxY;
        centerX = (int) x;
        centerY = (int) y;
        pinta();
    }
};
