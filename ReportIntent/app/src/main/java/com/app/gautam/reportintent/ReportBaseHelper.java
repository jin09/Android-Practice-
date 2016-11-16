package com.app.gautam.reportintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.gautam.reportintent.ReportDBSchema.ReportTable;

/**
 * Created by gautam on 12-10-2016.
 */
public class ReportBaseHelper extends SQLiteOpenHelper {


    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "reportBase.db";

    public ReportBaseHelper(Context context) {
        super(context,DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ ReportTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ReportTable.cols.UUID + ", " +
                ReportTable.cols.TITLE + ", " +
                ReportTable.cols.DATE + ", " +
                ReportTable.cols.RESOLVED +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
