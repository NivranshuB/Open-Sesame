package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent thisIntent = getIntent();

        String type = thisIntent.getStringExtra("type");

        if (type != null) {
            Toast.makeText(this, "Showing items of " + type, Toast.LENGTH_LONG).show();
        } else {
            String searchString = thisIntent.getStringExtra("id");
            Toast.makeText(this, "Search for item:  " + searchString, Toast.LENGTH_LONG)
                    .show();
        }

    }
}