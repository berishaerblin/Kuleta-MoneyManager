package com.berishaerblin.moneymanager.splashAndintro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.berishaerblin.moneymanager.MainActivity;
import com.berishaerblin.moneymanager.R;

public class SplashScreen extends AppCompatActivity {

    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageview = (ImageView)findViewById(R.id.splash_screen_logo);
        imageview.setImageResource(R.mipmap.ic_launcher);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splashscreen_effect_logo);
        imageview.setAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageview.setImageResource(R.color.colorPrimary);
                overridePendingTransition(0,0);
                finish();
                overridePendingTransition(0,0);
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
