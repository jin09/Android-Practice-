package com.app.gautam.reportintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by gautam on 30-09-2016.
 */
public class Report {

    private UUID mID;
    private String mtitle;
    private Date mDate;
    private boolean mResolved;

    public Report(UUID id) {
        mID = id;
        mDate = new Date();
    }

    public Report(){
        this(UUID.randomUUID());
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismResolved() {
        return mResolved;
    }

    public void setmResolved(boolean mResolved) {
        this.mResolved = mResolved;
    }


    public UUID getID(){
        return  mID;
    }
    public String getTitle(){
        return mtitle;
    }
    public void setTitle(String title){
        mtitle = title;
    }

}


