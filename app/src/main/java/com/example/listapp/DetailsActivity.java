package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_details);
        setSupportActionBar(toolbar);

        ViewCompat.setTransitionName(findViewById(R.id.details_image_view), "topPicksImageTransition");

    }
}