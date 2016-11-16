package com.app.gautam.sharedpreferenceapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static String PREFS_NAME = "MyPrefsFile";
    public boolean silent =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(getString(R.string.prefs_file_name), Context.MODE_APPEND);
        silent = settings.getBoolean("silentMode",false);
        Log.d("TEST", "Value " + silent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences("PREFS_FILE", Context.MODE_APPEND);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode",true);
        editor.commit();
    }
}
