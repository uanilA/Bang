package com.bang.module.home.newsfeed;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bang.R;

import java.util.ArrayList;


public class NewsFeedFragment extends Fragment {

    private Context mContext;

    public NewsFeedFragment() {
    }

    public static NewsFeedFragment newInstance() {
        NewsFeedFragment fragment = new NewsFeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        RecyclerView rcvNewsFeedList = view.findViewById(R.id.rcvNewsFeedList);
        rcvNewsFeedList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcvNewsFeedList.setLayoutManager(layoutManager);
        ArrayList<DataModel> data = new ArrayList<>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.versionArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }
        RecyclerView.Adapter adapter = new CustomAdapter(data);
        rcvNewsFeedList.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
