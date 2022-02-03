package com.example.examen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

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
        c.drawLine(iX(a.x), iY(a.y), iX(b.x), iY(b.y),p);
        return;
    }

    public void pinta(){
        minMaxXY=Math.min(maxX, maxY);
        centerX = maxX/2;
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

    public void pintaVolumen(double x[], double y[], double z[], double vol){
        int posx = maxX/2;
        int posy = maxY/10;
        String sx = "(";
        String sy = "(";
        String sz = "(";
        for(int i = 0; i<x.length;i++){
            sx = sx + x[i] + ",";
        }
        for(int i = 0; i<y.length;i++){
            sy = sy + y[i] + ",";
        }
        for(int i = 0; i<z.length;i++){
            sz = sz + z[i] + ",";
        }
        sx = sx.substring(0,sx.length()-1);
        sy = sy.substring(0,sy.length()-1);
        sz = sz.substring(0,sz.length()-1);
        sx = sx + ")";
        sy = sy + ")";
        sz = sz + ")";
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.RIGHT);
        p.setTextSize(35);
        c.drawText("Vector x: ",posx, posy,p);
        p.setColor(Color.RED);
        c.drawText(sx,posx,posy+30,p);
        p.setColor(Color.BLACK);
        c.drawText("Vector y: ",posx, posy+70,p);
        p.setColor(Color.RED);
        c.drawText(sy,posx,posy+100,p);
        p.setColor(Color.BLACK);
        c.drawText("Vector z: ",posx, posy+140,p);
        p.setColor(Color.RED);
        c.drawText(sz,posx,posy+170,p);
        p.setColor(Color.BLACK);
        c.drawText("Volumen: ",posx, posy+210,p);
        p.setColor(Color.RED);
        c.drawText(""+vol,posx,posy+240,p);
    }

    public void ejes(){
        p.setTextSize(30);
        p.setColor(Color.rgb(0, 0, 255)); // Ejes azules
        c.drawLine(maxX/2, 0, maxX/2, maxY, p);
        c.drawLine(0, maxY/2, maxX, maxY/2, p);
        c.drawLine(maxX,0,0,maxY,p);
        p.setColor(Color.RED);
        p.setTextAlign(Paint.Align.RIGHT);
        c.drawText("Eje X", maxX-5, maxY/2-10, p);
        p.setTextAlign(Paint.Align.CENTER);
        c.drawText("Eje Y", maxX/2+30, 20, p);
        p.setTextAlign(Paint.Align.RIGHT);
        c.drawText("Eje z", maxX-30, 50, p);
    }


};
