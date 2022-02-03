package com.example.examen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

public class Lienzo extends View implements View.OnTouchListener {
    Paint p;
    Perspectiva per;
    double x[];
    double y[];
    double z[];

    public Lienzo(Context c, double px[], double py[], double pz[]) {
        super(c);
        x = Arrays.copyOf(px,px.length);
        y = Arrays.copyOf(py,py.length);
        z = Arrays.copyOf(pz,pz.length);
    }


    protected void onDraw(Canvas c){
        super.onDraw(c);
        p=new Paint();
        p.setColor(Color.CYAN);
        c.drawPaint(p);
        //this.setOnTouchListener(this);
        per = new Perspectiva(c,p);
        per.pinta();
        per.ejes();
        per.pintaVolumen(x,y,z,determinante());
    }

    public double determinante ()
    {
        double determinante = 0.0;
        double mat1, mat2, mat3;
        mat1 = (y[1]*z[2])-(y[2]*z[1]);
        mat2 = (y[0]*z[2])-(y[2]-z[0]);
        mat3 = (y[0]*z[1])-(y[1]*z[0]);

        determinante = (x[0]*mat1) - (x[1]*mat2) + (x[2]*mat3);
        determinante = determinante * (1.0/6.0);
        determinante = Math.round(determinante*100.0)/100.0;
        determinante = Math.abs(determinante);
        Log.d("Determinante", ""+determinante);
        return determinante;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_HOVER_MOVE:
                Log.e("Dale","Movimiento iniciado: "+ event.getX() + "," + event.getY());

                per.movimiento((int)event.getX(),(int)event.getY());
                break;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                break;
        }
        return true;
    }
}
