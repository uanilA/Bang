package com.bang.module.authentication.country;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bang.R;
import com.bang.base.BangParentActivity;
import com.bang.module.authentication.country.adapter.CountryAdapter;
import com.bang.module.authentication.country.model.Country;
import com.bang.utils.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CountrySelectionActivity extends BangParentActivity implements View.OnClickListener {

    private RecyclerView countryListRecyclerView;
    private List<Country> mCountries;
    private long mLastClickTime = 0;
    private ImageView ivSelectCountryBack;
    private EditText etFilterField;
    CountryAdapter countryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);
        init();

        mCountries= new ArrayList<>();
        mCountries.addAll(Arrays.asList(
                new Gson().fromJson(Utility.loadJSONFromAsset(this,"countries.json"), Country[].class)));
        setChatHistoryAdapter();
        ivSelectCountryBack.setOnClickListener(this);
        etFilterField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(countryAdapter != null) {
                    countryAdapter.filter(s.toString());
                    //  customList1.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setChatHistoryAdapter() {
        countryAdapter = new CountryAdapter(this, mCountries);
        countryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryListRecyclerView.setAdapter(countryAdapter);
    }
     private void init() {
         ivSelectCountryBack = findViewById(R.id.ivSelectCountryBack);
         countryListRecyclerView = findViewById(R.id.countryListRecyclerView);
         etFilterField = findViewById(R.id.etFilterField);
     }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        etFilterField.clearFocus();
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if (v.getId() == R.id.ivSelectCountryBack) {
            finish();
        }
    }


}
