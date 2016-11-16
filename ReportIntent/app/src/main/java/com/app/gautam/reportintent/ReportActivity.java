package com.app.gautam.reportintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ReportActivity extends SingleFragmentActivity {

    public static final  String EXTRA_REPORT_ID = "report_intent_report_id";
    public static Intent newIntent(Context packageContext, UUID reportID){
        Intent intent = new Intent(packageContext, ReportActivity.class);
        intent.putExtra(EXTRA_REPORT_ID, reportID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID reportID = (UUID) getIntent().getSerializableExtra("uuid");
        return ReportFragment.newInstance(reportID);
    }
}
