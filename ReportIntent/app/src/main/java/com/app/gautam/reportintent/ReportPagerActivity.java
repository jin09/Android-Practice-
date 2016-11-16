package com.app.gautam.reportintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by gautam on 08-10-2016.
 */

public class ReportPagerActivity extends AppCompatActivity{
    private static final String EXTRA_REPORT_ID = "report_id";
    private ViewPager mViewPager;
    private List<Report> mReports;

    public static Intent newIntent(Context context, UUID report_id){
        Intent  intent = new Intent(context, ReportPagerActivity.class);
        intent.putExtra(EXTRA_REPORT_ID,report_id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_pager);

        UUID reportID = (UUID) getIntent().getSerializableExtra(EXTRA_REPORT_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_report_pager_view_pager);

        mReports = ReportStore.get(this).getmReports();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Report report = mReports.get(position);
                return ReportFragment.newInstance(report.getID());
            }

            @Override
            public int getCount() {
                return mReports.size();
            }
        });
        for(int i=0;i<mReports.size();i++){
            if(mReports.get(i).getID().equals(reportID)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
