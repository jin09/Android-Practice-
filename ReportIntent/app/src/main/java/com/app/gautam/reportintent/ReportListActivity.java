package com.app.gautam.reportintent;

import android.support.v4.app.Fragment;

/**
 * Created by gautam on 30-09-2016.
 */
public class ReportListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ReportListFragment();
    }
}
