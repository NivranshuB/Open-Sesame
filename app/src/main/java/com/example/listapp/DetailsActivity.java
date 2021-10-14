package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.util.Log;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Log.d("detailsActivity", "Details activity launched");
        System.out.println("Details activity launched");

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_details);
        setSupportActionBar(toolbar);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.description_relative_layout);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        relativeLayout.startAnimation(animation);

        ViewCompat.setTransitionName(findViewById(R.id.details_image_view), "topPicksImageTransition");

    }
}