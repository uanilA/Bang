package com.bang.module.home.nearyou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bang.R;
import com.bang.module.home.nearyou.DiscreteScrollViewOptions;
import com.bang.module.home.nearyou.Users;
import com.bang.module.home.nearyou.adapter.NearYouUserAdapter;
import com.bang.module.home.nearyou.model.Item;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;


public class NearYouFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener {

    private List<Item> data;
    private Users users;
    private DiscreteScrollView item_picker;
    private InfiniteScrollAdapter infiniteAdapter;

    public NearYouFragment() {
        // Required empty public constructor
    }


    public static NearYouFragment newInstance() {
        NearYouFragment fragment = new NearYouFragment();
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
        View view= inflater.inflate(R.layout.fragment_near_yoy, container, false);
        init(view);

        users = Users.get();
        data = users.getData();

        item_picker.setOrientation(DSVOrientation.HORIZONTAL);
        item_picker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new NearYouUserAdapter(data));
        item_picker.setAdapter(infiniteAdapter);
        item_picker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        item_picker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        onItemChanged(data.get(0));



        return view;
    }


    private void init(View view){
        item_picker = view.findViewById(R.id.item_picker);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void onItemChanged(Item item) {
        changeRateButtonState(item);
    }

    private void changeRateButtonState(Item item) {
        if (users.isRated(item.getId())) {
          //  rateItemButton.setImageResource(R.drawable.ic_star_black_24dp);
          //  rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopRatedStar));
        } else {
          //  rateItemButton.setImageResource(R.drawable.ic_star_border_black_24dp);
           // rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopSecondary));
        }
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(data.get(positionInDataSet));
    }
}
