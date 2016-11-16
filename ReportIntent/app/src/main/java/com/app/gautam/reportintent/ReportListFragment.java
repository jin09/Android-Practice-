package com.app.gautam.reportintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;

/**
 * Created by gautam on 06-10-2016.
 */
public class ReportListFragment extends Fragment {
    private RecyclerView mReportRecyclerView;
    private ReportAdapter mAdapter;
    private boolean mSubtitleVisible;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report_list, container, false);
        mReportRecyclerView = (RecyclerView) v.findViewById(R.id.report_recycler_view);
        mReportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_report_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subltitle);
        if(mSubtitleVisible){
            subtitleItem.setTitle("hide");
        }
        else{
            subtitleItem.setTitle("show");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_report:
                Report report = new Report();
                ReportStore.get(getActivity()).addReport(report);
                Intent intent = new ReportPagerActivity()
                        .newIntent(getActivity(),report.getID());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subltitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        ReportStore reportstore = ReportStore.get(getActivity());
        int reportCount = reportstore.getmReports().size();
        String subtitle = getString(R.string.subtitle_format, reportCount);


        if(mSubtitleVisible == false){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        ReportStore reportStore = ReportStore.get(getActivity());
        List<Report> reports = reportStore.getmReports();
        if(mAdapter == null) {
            mAdapter = new ReportAdapter(reports);
            mReportRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.setmReports(reports);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }

    private class ReportHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Report mReport;
        public TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mResolvedCheckBox;

        public ReportHolder(View itemView){
            super(itemView);
            mTitleTextView = (TextView)
            itemView.findViewById(R.id.list_item_report_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_report_date_title_text_view);
            mResolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_report_resolved_check_box);
        }

        private void bindReport(Report report){
            mReport = report;
            itemView.setOnClickListener(this);
            mTitleTextView.setText(mReport.getTitle());
            mDateTextView.setText(mReport.getmDate().toString());
            mResolvedCheckBox.setChecked(mReport.ismResolved());
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(),mReport.getTitle()+"clicked!!", Toast.LENGTH_SHORT).show();
            //Intent intent = ReportActivity.newIntent(getActivity(), mReport.getID());
            //startActivity(intent);


            //Intent i = new Intent(getActivity(), ReportActivity.class);
            //i.putExtra("uuid",mReport.getID());
            //startActivity(i);

            Intent intent = ReportPagerActivity.newIntent(getActivity(),mReport.getID());
            startActivity(intent);
        }
    }

    private class ReportAdapter extends RecyclerView.Adapter<ReportHolder>{
        private List<Report> mReports;
        public ReportAdapter (List<Report> reports){
            mReports = reports;
        }

        @Override
        public ReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_report, parent, false);
            return new ReportHolder(v);
        }

        @Override
        public void onBindViewHolder(ReportHolder holder, int position) {
            Report report = mReports.get(position);
            holder.bindReport(report);
        }

        @Override
        public int getItemCount() {
            return mReports.size();
        }

        public void setmReports(List<Report> reports){
            mReports = reports;
        }
    }
}
