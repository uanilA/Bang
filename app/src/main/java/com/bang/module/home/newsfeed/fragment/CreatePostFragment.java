package com.bang.module.home.newsfeed.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bang.R;
import com.bang.base.BaseFragment;
import com.bang.helper.Constant;
import com.bang.helper.CustomToast;
import com.bang.image.picker.ImagePicker;
import com.bang.image.picker.ImageRotator;
import com.bang.module.home.newsfeed.model.AddNewsResponse;
import com.bang.module.home.newsfeed.presenter.AddNewsFeedPresenter;
import com.bang.network.ApiCallback;
import com.bang.utils.ImageVideoUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static com.bang.image.picker.ImageRotator.getResizedBitmap;

/**
 * Created by anil
 * Date: 18/07/19
 * Time: 2:05 PM
 */

public class CreatePostFragment extends BaseFragment implements View.OnClickListener, ApiCallback.AddNewsFeedCallback {

    private LinearLayout llAddNewsFeedImage;
    private LinearLayout llAddNewsFeedVideo;
    private EditText etAddNewsFeedTitle;
    private ImageView ivNewsFeedImage;
    private Bitmap profileImageBitmap;
    private ImageView ivCrossImage;
    private Uri finalVideoUri;
    private Uri imageUri;
    private Uri thumbUri;
    File videoFile;
    File videoThumbFile;
    // private long mLastClickTime = 0;

    public CreatePostFragment() {
        // Required empty public constructor
    }


    public static CreatePostFragment newInstance() {
        CreatePostFragment fragment = new CreatePostFragment();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        init(view);

        llAddNewsFeedImage.setOnClickListener(this);
        llAddNewsFeedVideo.setOnClickListener(this);
        ivCrossImage.setOnClickListener(this);
        return view;
    }

    private void init(View view) {
        ivCrossImage = view.findViewById(R.id.ivCrossImage);
        llAddNewsFeedImage = view.findViewById(R.id.llAddNewsFeedImage);
        llAddNewsFeedVideo = view.findViewById(R.id.llAddNewsFeedVideo);
        etAddNewsFeedTitle = view.findViewById(R.id.etAddNewsFeedTitle);
        ivNewsFeedImage = view.findViewById(R.id.ivNewsFeedImage);
        view.findViewById(R.id.tvtNewsFeedPost).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        /*if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();*/

        switch (v.getId()) {
            case R.id.llAddNewsFeedImage:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        ImagePicker.pickImage(getActivity());
                    }
                } else {
                    ImagePicker.pickImage(getActivity());
                }
                break;
            case R.id.llAddNewsFeedVideo:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        showSetIntroVideoDialog(mContext);
                    }
                } else {
                    showSetIntroVideoDialog(mContext);
                }
                break;
            case R.id.ivCrossImage:
                ivCrossImage.setVisibility(View.GONE);
                ivNewsFeedImage.setImageResource(R.drawable.upload_img);
                videoFile = null;
                imageUri = null;
                thumbUri = null;
                break;
            case R.id.tvtNewsFeedPost:
                addNewsFeedApiCall();
                break;
        }
    }

    private void addNewsFeedApiCall() {
        if (etAddNewsFeedTitle.getText().toString().equals("")) {
            CustomToast.getInstance(mContext).showToast(mContext, "Please enter title");
        } else if (imageUri == null && finalVideoUri == null) {
            CustomToast.getInstance(mContext).showToast(mContext, "Please select image or video");
        } else {
            new AddNewsFeedPresenter(this, mContext).callAddNewsFeedApi(etAddNewsFeedTitle.getText().toString()
                    , imageUri, videoFile, videoThumbFile);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 234) {    // Image Picker
                thumbUri = null;
                videoFile = null;
                imageUri = ImagePicker.getImageURIFromResult(mContext, requestCode, resultCode, data);
                try {
                    Bitmap tempBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    int orientation = ImageRotator.getRotation(mContext, imageUri, true);
                    profileImageBitmap = ImageRotator.rotate(tempBitmap, orientation);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError error) {
                    CustomToast.getInstance(mContext).showToast(mContext, getResources().getString(R.string.alertOutOfMemory));
                }
                Glide.with(this).load(profileImageBitmap).apply(new RequestOptions().placeholder(ContextCompat.getDrawable(mContext, R.drawable.user_img))).into(ivNewsFeedImage);
                ivCrossImage.setVisibility(View.VISIBLE);
            }
            if (requestCode == Constant.REQUEST_VIDEO_CAPTURE || requestCode == Constant.SELECT_VIDEO_REQUEST) {
                Bitmap thumbBitmap;
                imageUri = null;
                try {
                    Uri videoUri = data.getData();
                    String videoFilePath = ImageVideoUtils.generatePath(videoUri, mContext);
                    if (videoFilePath.endsWith(".mp4") | videoFilePath.endsWith(".3gp")) {
                        videoFile = new File(videoFilePath);
                        // Get length of file in bytes
                        long fileSizeInBytes = videoFile.length();
                        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                        long fileSizeInKB = fileSizeInBytes / 1024;
                        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                        long fileSizeInMB = fileSizeInKB / 1024;
                        if (fileSizeInMB < 30) {
                            finalVideoUri = videoUri;
                            //  String finalVideoFilePath = videoFilePath;
                            thumbBitmap = ImageVideoUtils.getVidioThumbnail(videoFilePath); //ImageVideoUtil.getCompressBitmap();
                            int rotation = ImageRotator.getRotation(mContext, finalVideoUri, true);
                            thumbBitmap = ImageRotator.rotate(thumbBitmap, rotation);
                            thumbBitmap = getResizedBitmap(thumbBitmap, 300);
                            videoThumbFile = savebitmap(mContext, thumbBitmap, UUID.randomUUID() + ".jpg");
                            thumbUri = Uri.fromFile(new File(String.valueOf(videoThumbFile)));
                            ivNewsFeedImage.setImageBitmap(thumbBitmap);
                            ivCrossImage.setVisibility(View.VISIBLE);
                        } else {
                            CustomToast.getInstance(mContext).showToast(mContext, "Please take less than 30 Mb file");
                        }
                    } else {
                        CustomToast.getInstance(mContext).showToast(mContext, "Video format not supported");
                    }
                } catch (Exception e) {
                    //  Toast.makeText(this, getString(R.string.alertImageException), Toast.LENGTH_SHORT).show();
                } catch (OutOfMemoryError e) {
                    CustomToast.getInstance(mContext).showToast(mContext, "Out of memory");
                }
            }

        }
    }

    @Override
    public void onSuccessAddNewsFeed(AddNewsResponse addNewsResponse) {
        CustomToast.getInstance(mContext).showToast(mContext, addNewsResponse.getMessage());
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
}