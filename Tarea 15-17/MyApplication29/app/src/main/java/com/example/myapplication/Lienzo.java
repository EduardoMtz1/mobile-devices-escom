package com.example.myapplication;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View implements View.OnTouchListener {
    Paint p;
    Perspectiva per;
    public Lienzo(Context c) {
        super(c);
    }

    protected void onDraw(Canvas c){
        super.onDraw(c);
        p=new Paint();
        p.setColor(Color.YELLOW);
        c.drawPaint(p);
        //this.setOnTouchListener(this);
        per = new Perspectiva(c,p);
        per.pinta();


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
