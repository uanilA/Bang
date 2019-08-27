package com.bang.module.home.addsurvey.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bang.R;
import com.bang.TimeWheel.LoopView;
import com.bang.base.BangParentActivity;
import com.bang.base.BaseFragment;
import com.bang.helper.CustomToast;
import com.bang.module.home.addsurvey.AddSurveyActivity;
import com.mikesu.horizontalexpcalendar.HorizontalExpCalendar;
import com.mikesu.horizontalexpcalendar.common.Config;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateEventFragment extends BaseFragment implements View.OnClickListener {


    private View main_tool_bar;
    private TextView tv_am_pm_btn;
    private TextView tv_hour_time;
    private TextView tv_min_time;
    private String default_slot = "AM";

    /**
     * For Time selection
     */

    private static final int DEFAULT_MIN_HOUR = 1;
    private static final int DEFAULT_MIN_MIN = 0;
    private static final int DEFAULT_MIN_SEC = 0;
    private static final int DEFAULT_MAX_HOUR = 12;
    private static final int DEFAULT_MAX_MIN = 59;
    private static final int DEFAULT_MAX_SEC = 59;

    // public Button confirmBtn;
    private LoopView hourLoopView;
    private LoopView minLoopView;
    private LoopView secLoopView;
    private LoopView timeMeridiemView;

    private int hourPos = 0;
    private int minPos = 0;
    private int secPos = 0;
    private int timeMeridiemPos = 0;
    private String date;
    private ArrayList hourList = new ArrayList();
    private ArrayList minList = new ArrayList();
    private ArrayList secList = new ArrayList();
    private ArrayList merediumList = new ArrayList();

    private int minHour;
    private int maxHour;
    private int minMin;
    private int maxMin;
    private int minSec;
    private int maxSec;
    private long mLastClickTime = 0;
    private CardView dateEventParentCard;


    public DateEventFragment() {
        // Required empty public constructor
    }

    public static DateEventFragment newInstance() {
        DateEventFragment fragment = new DateEventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_event, container, false);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Option
        minHour = DEFAULT_MIN_HOUR;
        maxHour = DEFAULT_MAX_HOUR;
        minMin  = DEFAULT_MIN_MIN;
        maxMin  = DEFAULT_MAX_MIN;
        minSec  = DEFAULT_MIN_SEC;
        maxSec  = DEFAULT_MAX_SEC;

        long milliseconds = getLongFromyyyyMMdd(getStrTime());
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        if (milliseconds != -1) {
            calendar.setTimeInMillis(milliseconds);
            hourPos = calendar.get(Calendar.HOUR);
            minPos = calendar.get(Calendar.MINUTE);
            secPos = calendar.get(Calendar.SECOND);

            String[] date = getStrTime().split(" ");
            if (date[1].equals("AM")) {
                timeMeridiemPos = 0;
            } else if (date[1].equals("PM")) {
                timeMeridiemPos = 1;
            }
        }
        initialiseTimeWheel(view);
        tv_hour_time.setText(format2LenStr(hourPos + 1));
        tv_min_time.setText(format2LenStr(minPos));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(View view) {

        main_tool_bar = ((AddSurveyActivity) mContext).findViewById(R.id.main_tool_bar);
        main_tool_bar.setVisibility(View.GONE);
        tv_am_pm_btn = view.findViewById(R.id.tv_am_pm_btn);
        dateEventParentCard = view.findViewById(R.id.dateEventParentCard);
        dateEventParentCard.setNestedScrollingEnabled(true);
        tv_am_pm_btn.setOnClickListener(this);

        tv_hour_time = view.findViewById(R.id.tv_hour_time);
        tv_min_time = view.findViewById(R.id.tv_min_time);

        view.findViewById(R.id.tvDateEventNext).setOnClickListener(this);
        view.findViewById(R.id.tvDateEventSkip).setOnClickListener(this);

        /*Get Current date */
        date = getCurrentDateString();
        HorizontalExpCalendar calendar = view.findViewById(R.id.calendar);
        calendar.setHorizontalExpCalListener(new HorizontalExpCalendar.HorizontalExpCalListener() {
            @Override
            public void onChangeViewPager(Config.ViewPagerType viewPagerType) {
                Log.i("Calender", "onChangeViewPager: " + viewPagerType.name());
            }
            @Override
            public void onDateSelected(DateTime dateTime) {
                String dateStr = dateTime.toString();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
                Date dateObj = null;
                try {
                    dateObj = curFormater.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date = curFormater.format(dateObj);
                System.out.println("$$$$$$$$$$$$$$$$$$$$" + date);
            }

            @Override
            public void onCalendarScroll(DateTime dateTime) {
                Log.i("Calender", "onCalendarScroll: " + dateTime.toString());
            }
        });
    }

    private void initialiseTimeWheel(View view) {
        hourLoopView = view.findViewById(R.id.picker_hour);
        minLoopView = view.findViewById(R.id.picker_min);
        secLoopView = view.findViewById(R.id.picker_sec);
        timeMeridiemView = view.findViewById(R.id.picker_meridiem);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            hourLoopView.setNestedScrollingEnabled(true);
            minLoopView.setNestedScrollingEnabled(true);
        }

        //do not loop,default can loop
        hourLoopView.setNotLoop();
        minLoopView.setNotLoop();
        secLoopView.setNotLoop();
        timeMeridiemView.setNotLoop();

        //set loopview text btnTextsize
        int viewTextSize = 18;
        hourLoopView.setTextSize(viewTextSize);
        minLoopView.setTextSize(viewTextSize);
        secLoopView.setTextSize(viewTextSize);
        timeMeridiemView.setTextSize(viewTextSize);

        //set checked listen
        hourLoopView.setListener(item -> {
            hourPos = item;
            int hours = hourPos + 1;
            tv_hour_time.setText(format2LenStr(hours));
        });
        minLoopView.setListener(item -> {
            minPos = item;
            tv_min_time.setText(format2LenStr(minPos));
        });
        secLoopView.setListener(item -> secPos = item);
        timeMeridiemView.setListener(item -> timeMeridiemPos = item);
        initPickerViews();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        main_tool_bar.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        switch (v.getId()) {
            case R.id.tvDateEventNext:
                int hour = 0;
                int min = 0;
                String merediumText = "";
                try {
                    hour = Integer.parseInt(tv_hour_time.getText().toString().trim());
                    min = Integer.parseInt(tv_min_time.getText().toString().trim());
                    merediumText = tv_am_pm_btn.getText().toString().trim();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                StringBuilder sb = new StringBuilder();
                sb.append(hour);
                sb.append(":");
                sb.append(format2LenStr(min));
                sb.append(" ");
                sb.append(merediumText);

                if (date != null && !tv_hour_time.getText().toString().equals("") && !tv_min_time.getText().toString().equals("")
                        && !tv_am_pm_btn.getText().toString().equals("")) {
                    ((BangParentActivity) mContext).addFragment(ContactFragment.newInstance(date
                            , tv_hour_time.getText().toString().trim() +":"+
                                    tv_min_time.getText().toString().trim()), true, R.id.frameAddSurvey);
                } else {
                    CustomToast.getInstance(mContext).showToast(mContext, "Please select date and time");
                }

                break;
            case R.id.tvDateEventSkip:
                activity.finish();
                break;
            case R.id.tv_am_pm_btn:
                if (default_slot.equals("AM")) {
                    default_slot = "PM";
                    tv_am_pm_btn.setText(default_slot);
                } else {
                    default_slot = "AM";
                    tv_am_pm_btn.setText(default_slot);
                }
                break;
        }
    }


    /**
     * For Time Selection data
     */

    private static long getLongFromyyyyMMdd(String time) {
        SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    private static String getStrTime() {
        SimpleDateFormat dd = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        return dd.format(new Date());
    }


    private void initPickerViews() {

        int hourCount = maxHour;
        int minCount = maxMin;
        int secCount = maxSec;

        for (int i = 1; i <= hourCount; i++) {
            hourList.add(format2LenStr(i));
        }

        for (int j = 0; j <= minCount; j++) {
            minList.add(format2LenStr(j));
        }

        for (int k = 0; k <= secCount; k++) {
            secList.add(format2LenStr(k));
        }

        hourLoopView.setArrayList(hourList);
        hourLoopView.setInitPosition(hourPos);

        minLoopView.setArrayList(minList);
        minLoopView.setInitPosition(minPos);

        secLoopView.setArrayList(secList);
        secLoopView.setInitPosition(secPos);

        merediumList.add("AM");
        merediumList.add("PM");
        timeMeridiemView.setArrayList(merediumList);
        timeMeridiemView.setInitPosition(timeMeridiemPos);


    }

    private static String format2LenStr(int num) {
        return (num < 10) ? "0" + num : String.valueOf(num);
    }


}
