package com.example.segundoparcial;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BaseDatos extends Activity {
    EditText jetI, jetN;
    Button jbnA, jbnB, jbnC, jbnD;
    TextView jtvL;
    TableLayout tb;
    SQLiteDatabase sqld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basedatos_main);
        jetI = (EditText) findViewById(R.id.xetI);
        jetN = (EditText) findViewById(R.id.xetN);
        jbnA = (Button) findViewById(R.id.xbnA);
        jbnB = (Button) findViewById(R.id.xbnB);
        jbnC = (Button) findViewById(R.id.xbnC);
        tb = (TableLayout) findViewById(R.id.table);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "Datos", null, 1);
        sqld = dsqlh.getWritableDatabase();
        jbnA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nombre = jetI.getText().toString();
                String valor = jetN.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("nombre", nombre);
                cv.put("valor", valor);
                sqld.insert("Datos", null, cv);
                jetI.setText("");
                jetN.setText("");
                generaTabla();
            }
        });
        jbnB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nombre = jetI.getText().toString();
                String condic = "nombre=?";
                String[] args = {nombre};
                sqld.delete("Datos",condic,args);
                jetI.setText("");
                jetN.setText("");
                generaTabla();
            }
        });
        jbnC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nombre = jetI.getText().toString();
                String valor = jetN.getText().toString();
                String condic = "nombre=?";
                String[] args = {nombre};
                ContentValues cv = new ContentValues();
                cv.put("nombre",nombre);
                cv.put("valor", valor);
                sqld.update("Datos",cv,condic,args);
                jetI.setText("");
                jetN.setText("");
                generaTabla();
            }
        });

    }
    public void generaTabla(){
        int count = tb.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = tb.getChildAt(i);
            if (child instanceof TableRow)
                ((ViewGroup) child).removeAllViews();
        }
        String[] nombre = new String[10];
        String[] valor = new String[10];
        int i = 0;
        Cursor c = sqld.rawQuery("SELECT nombre,valor FROM Datos", null);
        if (c.moveToFirst()) {
            do {
                nombre[i] = c.getString(0);
                valor[i] = c.getString(1);
                i++;
            } while(c.moveToNext());
        }
        TableRow fila = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        fila.setLayoutParams(lp);
        fila.setBackgroundResource(R.drawable.borde_tabla);
        TextView tv = new TextView(this);
        tv.setText("Nombre");
        fila.addView(tv);
        TextView n = new TextView(this);
        n.setText("valor");
        fila.addView(n);
        tb.addView(fila, 0);
        for(int x = 0; x < i+1;x++){
            TableRow f = new TableRow(this);
            TableRow.LayoutParams lpf = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            f.setLayoutParams(lpf);
            f.setBackgroundResource(R.drawable.borde_tabla);
            TextView tvf = new TextView(this);
            tvf.setText(nombre[x]);
            f.addView(tvf);
            TextView nf = new TextView(this);
            nf.setText(valor[x]);
            f.addView(nf);
            tb.addView(f, x+1);
        }
    }
}
