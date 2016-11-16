package com.app.gautam.fragmentbasics;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class MainActivity extends FragmentActivity {
    private static String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();

        Fragment frag = fm.findFragmentById(R.id.fragment_container);

        if(frag == null){
            frag = new BasicFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,frag)
                    .commit();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"inside onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"inside onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"inside onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"inside onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"inside onStop");
    }

}
