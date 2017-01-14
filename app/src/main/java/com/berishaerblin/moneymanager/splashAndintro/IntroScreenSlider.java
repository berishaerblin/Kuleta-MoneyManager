package com.berishaerblin.moneymanager.splashAndintro;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.berishaerblin.moneymanager.MainActivity;
import com.berishaerblin.moneymanager.R;

public class IntroScreenSlider  extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    ImageButton btn_finish, btn_next, btn_prev;


    ViewPager mViewPager;
    ViewPagerAdapter adapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    int ngjyrat[] = { R.color.bg_screen1, R.color.bg_screen2, R.color.bg_screen3,R.color.bg_screen4 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        mViewPager = (ViewPager) findViewById(R.id.pager_introduction);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());


        addfragment("Pasqyrimi i të gjitha të hollave (shpenzimet dhe fitimet).",ngjyrat[0],R.drawable.ic_home);
        addfragment("Llogaritja e huazimeve.",ngjyrat[1],R.drawable.ic_huazimet);
        addfragment("Menagjimi më efikas për kursime.",ngjyrat[2],R.drawable.ic_kursimet);
        addfragment("Pasqyra për 12 muajt e vitit (hyrjet dhe daljet).",ngjyrat[3],R.drawable.ic_histori);


        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        btn_finish = (ImageButton) findViewById(R.id.btn_finish);
        btn_next = (ImageButton) findViewById(R.id.btn_next);
        btn_prev = (ImageButton) findViewById(R.id.btn_prev);


        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroScreenSlider.this, MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
        setPageViewIndicator();


    }

    public void addfragment(String teksti, int ngjyra, int fotoja){
        Page f = new Page();
        f.changeText(teksti);
        f.changeBG(ngjyra);
        f.changePhoto(fotoja);

        adapter.addFragment(f, "");
    }

    private void setPageViewIndicator() {

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position != 0) {
            btn_prev.setVisibility(View.VISIBLE);
        } else {
            btn_prev.setVisibility(View.GONE);
        }

        if (position == dotsCount - 1) {
            btn_next.setVisibility(View.GONE);
            btn_finish.setVisibility(View.VISIBLE);
        } else {
            btn_next.setVisibility(View.VISIBLE);
            btn_finish.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}