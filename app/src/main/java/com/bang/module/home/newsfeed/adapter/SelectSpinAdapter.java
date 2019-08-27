package com.bang.module.home.newsfeed.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.bang.R;
import com.bang.module.home.newsfeed.model.SelectReasonsModel;

import java.util.List;

public class SelectSpinAdapter extends ArrayAdapter<SelectReasonsModel.DataBean.UserReportReasonsListBean> {
    private List<SelectReasonsModel.DataBean.UserReportReasonsListBean> userReportReasonsListBeans;

    public SelectSpinAdapter(Context context, int textViewResourceId, List<SelectReasonsModel.DataBean.UserReportReasonsListBean> userReportReasonsListBeans) {
        super(context, textViewResourceId, userReportReasonsListBeans);
        this.userReportReasonsListBeans = userReportReasonsListBeans;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(final int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_reason_spinner_view, parent, false);
        final TextView tvSelectReasons = row.findViewById(R.id.tvSelectReasons);
        tvSelectReasons.setText(userReportReasonsListBeans.get(position).getOffendingContent());
        return row;
    }
}