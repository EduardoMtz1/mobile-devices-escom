package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
public class DbmsSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Nacimiento (nombre TEXT, dia TEXT, hora TEXT)";

    public DbmsSQLiteHelper(Context c, String s, CursorFactory cf, int v) {
        super(c, s, cf, v);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqld, int ov, int nv) {
        sqld.execSQL("DROP TABLE IF EXISTS Nacimiento");
        sqld.execSQL(sqlCreate);
    }
}