package com.example.segundoparcial;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
public class DbmsSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Datos (nombre TEXT, valor INTEGER)";

    public DbmsSQLiteHelper(Context c, String s, CursorFactory cf, int v) {
        super(c, s, cf, v);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqld, int ov, int nv) {
        sqld.execSQL("DROP TABLE IF EXISTS Datos");
        sqld.execSQL(sqlCreate);
    }
}