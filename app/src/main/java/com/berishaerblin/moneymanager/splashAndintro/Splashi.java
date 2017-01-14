package com.berishaerblin.moneymanager.splashAndintro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.berishaerblin.moneymanager.MainActivity;
import com.berishaerblin.moneymanager.R;

/**
 * Created by mergimkrasniqi on 1/14/17.
 */

public class Splashi extends Activity {

    ImageView imageview;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    boolean act;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        animation = AnimationUtils.loadAnimation(this, R.anim.splashscreen_effect_logo);
        imageview = (ImageView)findViewById(R.id.splash_screen_logo);
        imageview.setImageResource(R.mipmap.ic_launcher);
        imageview.setAnimation(animation);

        sp = getApplicationContext().getSharedPreferences("MyPrefs",MODE_PRIVATE);
        act = sp.getBoolean("first",false);

        if(!act) {
            edit = sp.edit();
            edit.putBoolean("first",true);
            edit.commit();

            startActivity(new Intent(Splashi.this,IntroScreenSlider.class));
            finish();
        } else {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    imageview.setImageResource(R.color.colorPrimary);
                    overridePendingTransition(0,0);
                    startActivity(new Intent(Splashi.this, MainActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                }
                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
}
