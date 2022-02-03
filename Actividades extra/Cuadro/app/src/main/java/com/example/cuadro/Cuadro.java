package com.example.cuadro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Cuadro extends Activity {
    int[][] cuadro;
    int i;
    Bundle b;
    TableLayout tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuadro_main);
        tb = (TableLayout) findViewById(R.id.table);
        int x = 0, y = 0;
        b = getIntent().getExtras();
        i =b.getInt("i");
        cuadro = new int[i][i];
        for(int j = 1; j<(i*i)+1;j++){
            if(j == 1){
                x = (i/2);
                y = 0;
                cuadro[x][y] = j;
            }else{
                int xprev = x;
                int yprev = y;
                x++;
                y--;
                if(x>i-1&&y<0){
                    x = xprev;
                    y = yprev+1;
                }else{
                    if(x>i-1){
                        x = 0;
                    }
                    if(y<0){
                        y = i-1;
                    }
                }
                if(cuadro[x][y]!= 0 ){
                    x = xprev;
                    y = yprev+1;
                }
                cuadro[x][y] = j;
            }
        }
        generaCuadro(cuadro);
    }

    public void generaCuadro(int[][] c){
        for(int j =0;j<i;j++){
            TableRow fila = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            fila.setLayoutParams(lp);
            fila.setBackgroundResource(R.drawable.borde_tabla);
            for(int k = 0; k<i;k++){
                TextView tv = new TextView(this);
                tv.setText(""+c[k][j]);
                fila.addView(tv);
            }
            tb.addView(fila, j);
        }
    }
}
