package com.techone.cryptoconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (Build.VERSION.SDK_INT >= 21) {
            //getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorlightblue)); //status bar or the time bar at the top
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        appName=findViewById(R.id.appname);
        nameAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(SplashScreenActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);



    }
    void nameAnimation() {
        for(int i=0;i<Constants.appName.length();i++) {
            final int x = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String currentText = appName.getText().toString();
                    appName.setText(currentText + Constants.appName.charAt(x));
                }
            },500 + 200*x);
        }
    }
}