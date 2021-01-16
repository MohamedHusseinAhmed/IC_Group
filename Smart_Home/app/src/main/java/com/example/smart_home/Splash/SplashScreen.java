package com.example.smart_home.Splash;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.MainActivity;
import com.example.smart_home.R;
import com.example.smart_home.activity.LoginActivity;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.WHITE)
                /*.withHeaderText("Welcome")
                .withFooterText("Footer")
                .withBeforeLogoText("Before Logo Text")*/
                .withAfterLogoText("Easy Life")
                .withLogo(R.drawable.iclogo);


        /*config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);*/
        config.getAfterLogoTextView().setTextColor(Color.BLACK);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
