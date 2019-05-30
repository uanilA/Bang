package com.bang.module.authentication.genderselection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bang.R;
import com.bang.application.session.Session;
import com.bang.helper.CustomToast;
import com.bang.module.authentication.baseactivity.BangParentActivity;
import com.bang.module.authentication.genderselection.manager.GenderManager;
import com.bang.module.authentication.genderselection.model.UpdateGenderResponse;
import com.bang.module.home.MainActivity;
import com.bang.serverhandling.ApiCallback;

public class GenderSelectionActivity extends BangParentActivity implements View.OnClickListener, ApiCallback.GenderSelectionCallback {

    private RelativeLayout rlMalePrefers,rlFemalePrefers,rlTransgenderMalePrefers
            ,rlTransgenderFemalePrefersMalePrefers,rlNonGenderPrefers;

    private ImageView ivNonGenderSelected,ivTransGenderFamaleSelected,ivTransGenderMaleSelected
            ,ivFemaleSelected,ivMaleSelected;

    private TextView tvNonGenderPrefer,tvTransgenderFemalePrefer,tvTransgenderMalePrefer
            ,tvFemalePrefer,tvMalePrefer;

    private LinearLayout llMaleGender,llFemaleGender,llTransgenderMale,llTransgemderFemale,llNonGender;

    private TextView tvNotGender,tvTransgenderFemale,txtTransGenderMale,tvFemaleGender,tvMaleGender;

    private TextView tvGenderSelectionSkip,tvGenderSelectionNext;
    private String preference="",myGender="";
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_selection);
        init();
        session = new Session(GenderSelectionActivity.this);
        rlMalePrefers.setOnClickListener(this);
        rlFemalePrefers.setOnClickListener(this);
        rlTransgenderMalePrefers.setOnClickListener(this);
        rlTransgenderFemalePrefersMalePrefers.setOnClickListener(this);
        rlNonGenderPrefers.setOnClickListener(this);

        llMaleGender.setOnClickListener(this);
        llFemaleGender.setOnClickListener(this);
        llTransgenderMale.setOnClickListener(this);
        llTransgemderFemale.setOnClickListener(this);
        llNonGender.setOnClickListener(this);
        tvGenderSelectionSkip.setOnClickListener(this);
        tvGenderSelectionNext.setOnClickListener(this);
    }

    private void init(){
        rlMalePrefers = findViewById(R.id.rlMalePrefers);
        rlFemalePrefers = findViewById(R.id.rlFemalePrefers);
        rlTransgenderMalePrefers = findViewById(R.id.rlTransgenderMalePrefers);
        rlTransgenderFemalePrefersMalePrefers = findViewById(R.id.rlTransgenderFemalePrefersMalePrefers);
        rlNonGenderPrefers = findViewById(R.id.rlNonGenderPrefers);

        ivTransGenderFamaleSelected = findViewById(R.id.ivTransGenderFamaleSelected);
        ivNonGenderSelected = findViewById(R.id.ivNonGenderSelected);
        ivTransGenderMaleSelected = findViewById(R.id.ivTransGenderMaleSelected);
        ivFemaleSelected = findViewById(R.id.ivFemaleSelected);
        ivMaleSelected = findViewById(R.id.ivMaleSelected);

        tvNonGenderPrefer = findViewById(R.id.tvNonGenderPrefer);
        tvTransgenderFemalePrefer = findViewById(R.id.tvTransgenderFemalePrefer);
        tvTransgenderMalePrefer = findViewById(R.id.tvTransgenderMalePrefer);
        tvFemalePrefer = findViewById(R.id.tvFemalePrefer);
        tvMalePrefer = findViewById(R.id.tvMalePrefer);

        llMaleGender = findViewById(R.id.llMaleGender);
        llFemaleGender = findViewById(R.id.llFemaleGender);
        llTransgenderMale = findViewById(R.id.llTransgenderMale);
        llTransgemderFemale = findViewById(R.id.llTransgemderFemale);
        llNonGender = findViewById(R.id.llNonGender);

        tvNotGender = findViewById(R.id.tvNotGender);
        tvTransgenderFemale = findViewById(R.id.tvTransgenderFemale);
        txtTransGenderMale = findViewById(R.id.txtTransGenderMale);
        tvFemaleGender = findViewById(R.id.tvFemaleGender);
        tvMaleGender = findViewById(R.id.tvMaleGender);
        tvGenderSelectionSkip = findViewById(R.id.tvGenderSelectionSkip);
        tvGenderSelectionNext = findViewById(R.id.tvGenderSelectionNext);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.rlMalePrefers:
                 selectMalePrefer();
                 break;
             case R.id.rlFemalePrefers:
                 selectFemalePrefer();
                 break;
             case R.id.rlTransgenderMalePrefers:
                 selectTransgenderMalePrefer();
                 break;
             case R.id.rlTransgenderFemalePrefersMalePrefers:
                 selectTransgenderFemalePrefer();
                 break;
             case R.id.rlNonGenderPrefers:
                 selectNonGenderPrefer();
                 break;
             case R.id.llMaleGender:
                  selectedMaleGender();
                 break;
             case R.id.llFemaleGender:
                 selectedFemaleGender();
                 break;
             case R.id.llTransgenderMale:
                 selectedTransgenderMaleGender();
                 break;
             case R.id.llTransgemderFemale:
                 selectedTransgenderFemaleGender();
                 break;
             case R.id.llNonGender:
                 selectedNonGender();
                 break;
             case R.id.tvGenderSelectionSkip:
                 startActivity(new Intent(GenderSelectionActivity.this, MainActivity.class));
                 break;
             case R.id.tvGenderSelectionNext:
                 if (myGender.equals("")){
                     CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this,"Please select Preference");
                 }else if (preference.equals("")){
                     CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this,"Please select your gender");
                 }else {
                     new GenderManager(this,GenderSelectionActivity.this).callUpdateGenderApi(myGender,preference);

                 }
                 break;
         }
    }


    private void selectMalePrefer(){

        myGender="0";

        rlMalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.VISIBLE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivNonGenderSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectFemalePrefer(){

        myGender="1";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.VISIBLE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivNonGenderSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectTransgenderMalePrefer(){

        myGender="2";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.VISIBLE);
        ivNonGenderSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectTransgenderFemalePrefer(){

        myGender="3";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.gender_selected_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.login_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.VISIBLE);
        ivNonGenderSelected.setVisibility(View.GONE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorBang));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectNonGenderPrefer(){

        myGender="4";

        rlMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlFemalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlTransgenderFemalePrefersMalePrefers.setBackgroundResource(R.drawable.login_background);
        rlNonGenderPrefers.setBackgroundResource(R.drawable.gender_selected_background);

        ivMaleSelected.setVisibility(View.GONE);
        ivFemaleSelected.setVisibility(View.GONE);
        ivTransGenderMaleSelected.setVisibility(View.GONE);
        ivTransGenderFamaleSelected.setVisibility(View.GONE);
        ivNonGenderSelected.setVisibility(View.VISIBLE);

        tvMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderMalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemalePrefer.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNonGenderPrefer.setTextColor(getResources().getColor(R.color.colorBang));
    }

    private void selectedMaleGender(){

        preference="0";

        llMaleGender.setBackgroundResource(R.drawable.doted_background_corners);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorBang));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));

    }

    private void selectedFemaleGender(){

        preference="1";

        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.doted_background_corners);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorBang));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectedTransgenderMaleGender(){

        preference="2";

        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.doted_background_corners);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorBang));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectedTransgenderFemaleGender(){

        preference="3";

        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.doted_background_corners);
        llNonGender.setBackgroundResource(R.drawable.not_selected_image_border);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorBang));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
    }

    private void selectedNonGender(){

        preference = "4";
        llMaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llFemaleGender.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgenderMale.setBackgroundResource(R.drawable.not_selected_image_border);
        llTransgemderFemale.setBackgroundResource(R.drawable.not_selected_image_border);
        llNonGender.setBackgroundResource(R.drawable.doted_background_corners);

        tvMaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvFemaleGender.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        txtTransGenderMale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvTransgenderFemale.setTextColor(getResources().getColor(R.color.colorSelectCountry));
        tvNotGender.setTextColor(getResources().getColor(R.color.colorBang));
    }

    @Override
    public void onGenderSelectionResponse(UpdateGenderResponse sliderRespone) {
        startActivity(new Intent(GenderSelectionActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onTokenChangeError(String errorMessage) {
           session.logout();
    }

    @Override
    public void onShowBaseLoader() {
           showLoader();
    }

    @Override
    public void onHideBaseLoader() {
          hideLoader();
    }

    @Override
    public void onError(String errorMessage) {
        CustomToast.getInstance(GenderSelectionActivity.this).showToast(GenderSelectionActivity.this,errorMessage);
    }
}
