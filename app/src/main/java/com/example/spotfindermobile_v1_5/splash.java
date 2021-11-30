/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotfindermobile_v1_5.ui.login.LoginActivity;

public class splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    // variables
    Animation bottomAnim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // animation
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        // hooks
        image = findViewById(R.id.splash_logo);

        image.setAnimation(bottomAnim);

        // run animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( com.example.spotfindermobile_v1_5.splash.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}