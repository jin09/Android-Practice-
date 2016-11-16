package com.app.gautam.fragmentadvance;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class MainActivity extends FragmentActivity implements ReportFragment.onCheckBoxSelectedListener{
    private static String TAG = "MAIN_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();

        Fragment frag = fm.findFragmentById(R.id.vert);

        if(frag == null){
            frag = new ReportFragment();
            fm.beginTransaction()
                    .add(R.id.vert,frag)
                    .commit();
        }
    }

    @Override
    public void onCheckBoxSelected() {
        Log.d(TAG,"Check Box Selected..");
        ReportFragment mreportfragment = (ReportFragment)getSupportFragmentManager().findFragmentById(R.id.vert);
        if(mreportfragment != null) {
            mreportfragment.calledFromActivity();
        }

        FragmentManager fm = getSupportFragmentManager();

        Fragment frag = new DetailFragment();

        if(frag == null){
            fm.beginTransaction()
                    .add(R.id.vert,frag)
                    .commit();
        }
        else{
            fm.beginTransaction()
                    .replace(R.id.vert,frag)
                    .commit();
        }
    }
}
