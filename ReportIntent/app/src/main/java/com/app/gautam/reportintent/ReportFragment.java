package com.app.gautam.reportintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by gautam on 30-09-2016.
 */
public class ReportFragment extends Fragment{

    private Report mReport;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mResolvedCheckbox;
    private static final String ARGUMENT_REPORT_ID = "report_id";
    private static final String DIALOG_DATE = "DialogDate";
    int REQUEST_DATE = 10;

    public static ReportFragment newInstance(UUID reportID){
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_REPORT_ID, reportID);
        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID reportID = (UUID) getArguments().getSerializable(ARGUMENT_REPORT_ID);
        if(reportID == null){
            mReport = new Report();
        }
        else {
            mReport = ReportStore.get(getActivity()).getReport(reportID);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        ReportStore.get(getActivity())
                .updateReport(mReport);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report,container, false);
        mTitleField = (EditText) v.findViewById(R.id.report_title);
        mTitleField.setText(mReport.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mReport.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.report_date);
        mDateButton.setText(mReport.getmDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mReport.getmDate());

                dialog.setTargetFragment(ReportFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        mResolvedCheckbox = (CheckBox) v.findViewById(R.id.report_resolved);
        mResolvedCheckbox.setChecked(mReport.ismResolved());
        mResolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mReport.setmResolved(isChecked);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        else{
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mReport.setmDate(date);
            mDateButton.setText(mReport.getmDate().toString());
        }
    }
}
