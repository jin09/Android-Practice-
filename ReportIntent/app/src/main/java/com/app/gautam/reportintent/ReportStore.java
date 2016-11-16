package com.app.gautam.reportintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by gautam on 30-09-2016.
 */
public class ReportStore {
    private static ReportStore sReportStore;
   // private List<Report> mReports;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static ReportStore get(Context context){
        if(sReportStore == null){
            sReportStore = new ReportStore(context);
        }
        return sReportStore;
    }
    private ReportStore(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ReportBaseHelper(mContext)
                .getWritableDatabase();
      //  mReports = new ArrayList<>();
       /*
       for(int i=0;i<100;i++){
            Report report = new Report();
            report.setTitle("Report #"+i);
            report.setmResolved(i%2 == 0);
            mReports.add(report);
        }
        */
    }

    public void addReport(Report r){
     //   mReports.add(r);
        ContentValues values = getContentValues(r);

        mDatabase.insert(ReportDBSchema.ReportTable.NAME,null,values);
    }

    public void updateReport(Report report){
        String uuidString = report.getID().toString();
        ContentValues values = getContentValues(report);

        mDatabase.update(ReportDBSchema.ReportTable.NAME,values,
                ReportDBSchema.ReportTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Report> getmReports(){
     //   return mReports;
        List<Report> reports = new ArrayList<>();
        ReportCursorWrapper cursor = queryReport(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                reports.add(cursor.getReport());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return reports;
    }

    public Report getReport(UUID id){
       /* for(Report report : mReports){
            if(report.getID().equals(id)){
                return report;
            }
        }
        */
        ReportCursorWrapper cursor = queryReport(
                ReportDBSchema.ReportTable.cols.UUID + " = ?",
                new String[] {id.toString()}
        );
        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getReport();
        }
        finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Report report){
        ContentValues values = new ContentValues();
        values.put(ReportDBSchema.ReportTable.cols.UUID,report.getID().toString());
        values.put(ReportDBSchema.ReportTable.cols.TITLE,report.getTitle());
        values.put(ReportDBSchema.ReportTable.cols.DATE,report.getmDate().toString());
        values.put(ReportDBSchema.ReportTable.cols.RESOLVED,report.ismResolved()?1:0);

        return values;
    }

    private ReportCursorWrapper queryReport(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ReportDBSchema.ReportTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ReportCursorWrapper(cursor);
    }


}
