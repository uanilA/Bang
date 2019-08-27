package com.bang.module.home.profile.bandrequest;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.base.ClickListener;
import com.bang.helper.CustomToast;
import com.bang.module.home.profile.bandrequest.adapter.BangRequestAdapter;
import com.bang.module.home.profile.bandrequest.model.AcceptRejectModel;
import com.bang.module.home.profile.bandrequest.model.BangRequestsModel;
import com.bang.module.home.profile.bandrequest.presenter.BangRequestPresenter;
import com.bang.module.home.profile.bandrequest.presenter.BangRequestUpdatePresenter;
import com.bang.network.ApiCallback;

import java.util.ArrayList;
import java.util.List;

public class BangRequestFragment extends BaseFragment implements ApiCallback.BangRequestsDataCallback,ApiCallback.BangRequestUpdateCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private int offset = 0;
    private RecyclerView recycleBangRequestList;
    private List<BangRequestsModel.DataBean.BangRequestListBean> bangRequestListBeans;
    private BangRequestAdapter bangRequestAdapter;
    private View no_survey_avail;

    public BangRequestFragment() {
    }
   // String param1, String param2
    public static BangRequestFragment newInstance() {
        BangRequestFragment fragment = new BangRequestFragment();
        Bundle args = new Bundle();
      //  args.putString(ARG_PARAM1, param1);
     //   args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_bang_request, container, false);
        init(view);
        bangRequestAdapter = new BangRequestAdapter(bangRequestListBeans, mContext , new ClickListener.RequestStatusClick() {
            @Override
            public void StatusUpdateClick(String val, int position) {
                requestUpdateStatus(String.valueOf(bangRequestListBeans.get(position).getBangConnectionId()),val);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        recycleBangRequestList.setAdapter(bangRequestAdapter);
        recycleBangRequestList.setLayoutManager(gridLayoutManager);
        return view;
    }

    private void requestUpdateStatus(String connectionId,String status) {
        new BangRequestUpdatePresenter(this,mContext).requestUpdateApiCall(connectionId,status);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bangRequestListBeans.clear();
        apiCalling(offset);
    }

    private void apiCalling(int myOffset) {
        new BangRequestPresenter(this,mContext).bangRequestApiCall(myOffset);
    }

    private void init(View view) {
        bangRequestListBeans = new ArrayList<>();
        recycleBangRequestList = view.findViewById(R.id.recycleBangRequestList);
        no_survey_avail = view.findViewById(R.id.no_survey_avail);
        TextView tvTitleNotYet = view.findViewById(R.id.tvTitleNotYet);
        view.findViewById(R.id.tvDetailOfNewsFeed).setVisibility(View.GONE);
        tvTitleNotYet.setText(getString(R.string.no_bang_request));
    }

    @Override
    public void onSuccessRequests(BangRequestsModel bangRequestsModel) {
        if (bangRequestsModel.getData().getBang_request_list().size() > 0) {
            no_survey_avail.setVisibility(View.GONE);
            recycleBangRequestList.setVisibility(View.VISIBLE);
            bangRequestListBeans.addAll(bangRequestsModel.getData().getBang_request_list());
            bangRequestAdapter.notifyDataSetChanged();
        }else {
            no_survey_avail.setVisibility(View.VISIBLE);
            recycleBangRequestList.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccessRequestUpdate(AcceptRejectModel acceptRejectModel) {

    }

    @Override
    public void onTokenChangeError(String errorMessage) {
        activity.showDialog(mContext,errorMessage);
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
        CustomToast.getInstance(mContext).showToast(mContext,errorMessage);
    }
}
