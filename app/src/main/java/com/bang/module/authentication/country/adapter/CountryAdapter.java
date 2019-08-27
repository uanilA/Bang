package com.bang.module.authentication.country.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bang.R;
import com.bang.helper.Utils;
import com.bang.module.authentication.country.model.Country;
import com.bang.utils.Utility;
import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Country> mCountries;
    private List<Country> globlemCountry;
    private int[] flags = Utility.countryFlags;
    // private int selectedPosition = -1;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;


    public void filter(String charText) {
        charText = charText.toLowerCase();
        mCountries.clear();
        if (charText.length() == 0) {
            mCountries.addAll(globlemCountry);
        } else {
            for (int i = 0; i < globlemCountry.size(); i++) {
                if (globlemCountry.get(i).getCountryName().toLowerCase().startsWith(charText)) {
                    mCountries.add(globlemCountry.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public CountryAdapter(Context mContext, List<Country> mCountries) {
        this.mContext = mContext;
        this.mCountries = mCountries;
        this.globlemCountry = new ArrayList<>();
        if (mCountries != null) {
            globlemCountry.addAll(mCountries);
        }

        for (int i = 0; i < mCountries.size(); i++) {
            globlemCountry.get(i).setFlag(flags[i]);
        }
        // Collections.reverse(mCountries);
    }

    @NonNull
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_view, viewGroup, false);
        return new CountryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CountryAdapter.MyViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvCountryName.setText(mCountries.get(i).getCountryName());
        viewHolder.tvCountryCode.setText("+" + mCountries.get(i).getPhoneCode());
        Glide.with(mContext).load(mCountries.get(i).getFlag()).error(R.drawable.logo).into(viewHolder.ivCountryImage);
        viewHolder.rbSelectCountry.setOnCheckedChangeListener(null);

        viewHolder.rbSelectCountry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Utils.hideKeyboard(((Activity) mContext));
                    //    selectedPosition = getAdapterPosition();
                    viewHolder.rbSelectCountry.setChecked(true);
                    mRunnable = new Runnable() {
                        @Override
                        public void run() {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("country_flag", mCountries.get(i).getFlag());
                            resultIntent.putExtra("country_code", String.valueOf(mCountries.get(i).getPhoneCode()));
                            resultIntent.putExtra("countryFlagCode",mCountries.get(i).getCode());
                            ((Activity) mContext).setResult(Activity.RESULT_OK, resultIntent);
                            ((Activity) mContext).finish();

                        }
                    };
                    mHandler.postDelayed(mRunnable, 500);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CircularImageView ivCountryImage;
        private TextView tvCountryName, tvCountryCode;
        private RadioButton rbSelectCountry;
        private CardView cvCountryLayout;

         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCountryImage = itemView.findViewById(R.id.ivCountryImage);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvCountryCode = itemView.findViewById(R.id.tvCountryCode);
            rbSelectCountry = itemView.findViewById(R.id.rbSelectCountry);
            cvCountryLayout = itemView.findViewById(R.id.cvCountryLayout);
            cvCountryLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cvCountryLayout) {
                Utils.hideKeyboard(((Activity) mContext));
                rbSelectCountry.setChecked(true);
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("country_flag", mCountries.get(getAdapterPosition()).getFlag());
                        resultIntent.putExtra("country_code", String.valueOf(mCountries.get(getAdapterPosition()).getPhoneCode()));
                        ((Activity) mContext).setResult(Activity.RESULT_OK, resultIntent);
                        ((Activity) mContext).finish();

                    }
                };
                mHandler.postDelayed(mRunnable, 500);
            }
        }
    }
}