package com.app.gautam.fragmentadvance;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by gautam on 09-09-2016.
 */
public class ReportFragment extends Fragment {
    private CheckBox mcheckbox;
    private  onCheckBoxSelectedListener mcallback;
    private static String TAG = "REPORT_FRAGMRNT";
    public interface onCheckBoxSelectedListener{
        public void onCheckBoxSelected();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_report,container,false);
        mcheckbox = (CheckBox)v.findViewById(R.id.report_checked);
        mcheckbox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mcallback.onCheckBoxSelected();
                    }
                }
        );
        return v;
    }

    public void calledFromActivity(){
        Log.d(TAG,"This method is called from the main activity!!!!");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mcallback = (onCheckBoxSelectedListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" should implement onCheckBoxSelectedListener..");
        }
    }
}
