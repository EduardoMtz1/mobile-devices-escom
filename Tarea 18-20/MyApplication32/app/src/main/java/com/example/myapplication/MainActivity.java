package com.example.myapplication;

import android.app.*;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import java.util.*;
public class MainActivity extends Activity implements OnClickListener {
    Button jbnF, jbnH, jbni, jbnl;
    EditText txtDate, txtTime, txtUser;
    TextView txtlist;
    int a, m, d, h, n;
    SQLiteDatabase sqld;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbnF = (Button) findViewById(R.id.xbnF);
        jbnF.setOnClickListener(this);
        jbnH = (Button) findViewById(R.id.xbnH);
        jbnH.setOnClickListener(this);
        jbni = (Button) findViewById(R.id.xbnI);
        jbni.setOnClickListener(this);
        jbnl = (Button) findViewById(R.id.xbnL);
        jbnl.setOnClickListener(this);
        txtlist = (TextView) findViewById(R.id.xtvL);
        txtUser = (EditText) findViewById(R.id.xetN);
        txtDate = (EditText) findViewById(R.id.xetF);
        txtTime = (EditText) findViewById(R.id.xetH);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBNacimiento", null, 1);
        sqld = dsqlh.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        if (v == jbnF) {
            Calendar c = Calendar.getInstance();
            a = c.get(Calendar.YEAR);
            m = c.get(Calendar.MONTH);
            d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker dp, int ye, int mo, int di) {
                    txtDate.setText(di + "-" + (mo + 1) + "-" + ye);
                }
            }, a, m, d);
            dpd.show();
        }
        if (v == jbnH) {
            Calendar c = Calendar.getInstance();
            h = c.get(Calendar.HOUR_OF_DAY);
            n = c.get(Calendar.MINUTE);
            TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker tp, int ho, int mi) {
                    txtTime.setText(ho + ":" + mi);
                }
            }, h, n, false);
            tpd.show();
        }
        if(v == jbni){
            String nombre = txtUser.getText().toString();
            String fecha = txtDate.getText().toString();
            String hora = txtTime.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("nombre", nombre);
            cv.put("dia", fecha);
            cv.put("hora", hora);
            sqld.insert("Nacimiento", null, cv);
            txtUser.setText("");
            txtDate.setText("");
            txtTime.setText("");
        }
        if(v == jbnl){
            String nombre, dia, hora;
            Cursor c = sqld.rawQuery("SELECT nombre, dia, hora FROM Nacimiento", null);
            txtlist.setText("");
            if(c.moveToFirst()){
                do{
                    nombre = c.getString(0);
                    dia = c.getString(1);
                    hora = c.getString(2);
                    txtlist.append(" " + nombre + "\t" + " " + dia + " " + hora + "\n");
                }while (c.moveToNext());
            }
        }

    }
}