package com.bang.module.home.newsfeed.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.helper.CustomToast;
import com.bang.module.home.newsfeed.adapter.SelectSpinAdapter;
import com.bang.module.home.newsfeed.model.ReportUserModel;
import com.bang.module.home.newsfeed.model.SelectReasonsModel;
import com.bang.module.home.newsfeed.presenter.ReportToUserPresenter;
import com.bang.network.ApiCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anil
 * Date: 26/07/19
 * Time: 12:05 PM
 */

public class ReportFragment extends BaseFragment implements ApiCallback.ReportToUserCallback, View.OnClickListener {

    private Spinner spinSelectReasons;
    private List<SelectReasonsModel.DataBean.UserReportReasonsListBean> userReportReasonsListBeans;
    private EditText etReportDescription;
    private String userReasons = "";
    private static final String ARG_PARAM1 = "report_To_userId";
    private static final String ARG_PARAM2 = "report_news_feedId";
    private String report_news_feedId;
    private String report_To_userId;
    SelectReasonsModel.DataBean.UserReportReasonsListBean reasonsListBean;

    public ReportFragment() {
    }

    public static ReportFragment newInstance(String report_To_userId, String report_news_feedId) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, report_To_userId);
        args.putString(ARG_PARAM2, report_news_feedId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            report_To_userId = getArguments().getString(ARG_PARAM1);
            report_news_feedId = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        init(view);
        return view;
    }

    private void init(@NonNull View view) {
        etReportDescription = view.findViewById(R.id.etReportDescription);
        spinSelectReasons = view.findViewById(R.id.spinSelectReasons);
        reasonsListBean = new SelectReasonsModel.DataBean.UserReportReasonsListBean();
        view.findViewById(R.id.tvDoneReportUser).setOnClickListener(this);
        apiCalling();

        spinSelectReasons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userReasons = userReportReasonsListBeans.get(position).getOffendingContent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void apiCalling() {
        new ReportToUserPresenter(this, mContext).callSelectReasonsApi();
    }

    private void setSpinAdapter() {
        spinSelectReasons.setAdapter(new SelectSpinAdapter(mContext, R.layout.select_reason_spinner_view, userReportReasonsListBeans));
    }

    @Override
    public void onSuccessSelectReasons(SelectReasonsModel selectReasonsModel) {
        userReportReasonsListBeans = new ArrayList<>(selectReasonsModel.getData().getUser_report_reasons_list());
        reasonsListBean.setOffendingContent("Select Reasons");
        userReportReasonsListBeans.add(0,reasonsListBean);
        setSpinAdapter();
    }

    @Override
    public void onSuccessReportToUser(ReportUserModel reportUserModel) {
                  CustomToast.getInstance(mContext).showToast(mContext,reportUserModel.getMessage());
                  activity.finish();
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext, errorMessage);
    }

    @Override
    public void onShowBaseLoader() {
        activity.showLoader();
    }

    @Override
    public void onHideBaseLoader() {
        activity.hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(mContext).showToast(mContext, errorMessage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvDoneReportUser) {
            if (userReasons.equals("Select Reasons")){
                CustomToast.getInstance(mContext).showToast(mContext,"Please select Reasons");
            }else if (etReportDescription.getText().toString().equals("")){
                CustomToast.getInstance(mContext).showToast(mContext,"Please enter Description");
            }else {
                reportToUserApiCalling();
            }
        }
    }

    private void reportToUserApiCalling() {
        new ReportToUserPresenter(this, mContext).callReportToUserApi(report_To_userId, report_news_feedId
                , userReasons, etReportDescription.getText().toString());
    }
}
