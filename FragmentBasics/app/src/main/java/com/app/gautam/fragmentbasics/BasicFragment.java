package com.app.gautam.fragmentbasics;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gautam on 04-09-2016.
 */
public class BasicFragment extends Fragment {

    private static String TAG = "Basic Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d(TAG,"inside onCreateView");
        View v = inflater.inflate(R.layout.fragment_basic,container,false);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"inside onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"inside onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"inside onDestroy");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"inside onActivityCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"inside onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"inside onDetach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"inside onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"inside onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"inside onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"inside onStop");
    }
}
