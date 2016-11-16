package com.app.gautam.reportintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by gautam on 12-10-2016.
 */
public class ReportCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ReportCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Report getReport(){
        String uuidString = getString(getColumnIndex(ReportDBSchema.ReportTable.cols.UUID));
        String title = getString(getColumnIndex(ReportDBSchema.ReportTable.cols.TITLE));
        long date = getLong(getColumnIndex(ReportDBSchema.ReportTable.cols.DATE));
        int isResolved = getInt(getColumnIndex(ReportDBSchema.ReportTable.cols.RESOLVED));

        Report report = new Report(UUID.fromString(uuidString));
        report.setTitle(title);
        report.setmDate(new Date(date));
        report.setmResolved(isResolved != 0);

        return report;
    }
}
