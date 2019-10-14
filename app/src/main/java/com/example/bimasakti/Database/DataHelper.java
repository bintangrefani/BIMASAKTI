package com.example.bimasakti.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "bimasakti.db";
    private static final int DB_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE bimasakti(ID integer PRIMARY KEY, LABEL text NULL, " +
                "NBVISITS text NULL, STATUS text NULL);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }


    //Methode ini untuk melakukan proses upgrade pada perubahan table dan skema database
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String sqlUpgrade = "DROP TABLE IF EXISTS mahasiswa";
        db.execSQL(sqlUpgrade);
        onCreate(db);
    }

}

