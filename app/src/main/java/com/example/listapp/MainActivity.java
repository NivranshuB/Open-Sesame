package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.provider.Settings;

import com.example.listapp.adapters.PanelViewAdapter;
import com.example.listapp.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //variables
    private ArrayList<Item> panelItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        createCategoryClickListeners();

        CardView cardView = (CardView) findViewById(R.id.card_view_1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(getBaseContext(), DetailsActivity.class);
                startActivity(detailIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }


    private void initPanelItems() {
        // TODO: 05/10/2021 Requires implementation of code to retrieve data from Firestore DB and helper functions to sort and etc, in order to populate panelItems list.
    }


    private void initPanelRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView panelRecyclerView = findViewById(R.id.panelRecyclerView);
        panelRecyclerView.setLayoutManager(linearLayoutManager);
        PanelViewAdapter panelViewAdapter = new PanelViewAdapter(panelItems, this);
        panelRecyclerView.setAdapter(panelViewAdapter);
    }

    private void createCategoryClickListeners() {
        RelativeLayout relativeLayoutWooden = (RelativeLayout) findViewById(R.id.relative_layout_wooden);
        relativeLayoutWooden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent woodenIntent = new Intent(getBaseContext(), ListActivity.class);
                woodenIntent.putExtra("categoryName", "wooden");
                startActivity(woodenIntent);
            }
        });

        RelativeLayout relativeLayoutMetal = (RelativeLayout) findViewById(R.id.relative_layout_metal);
        relativeLayoutMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent woodenIntent = new Intent(getBaseContext(), ListActivity.class);
                woodenIntent.putExtra("categoryName", "metal");
                startActivity(woodenIntent);
            }
        });

        RelativeLayout relativeLayoutGlass = (RelativeLayout) findViewById(R.id.relative_layout_glass);
        relativeLayoutGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent woodenIntent = new Intent(getBaseContext(), ListActivity.class);
                woodenIntent.putExtra("categoryName", "glass");
                startActivity(woodenIntent);
            }
        });

        RelativeLayout relativeLayoutHandles = (RelativeLayout) findViewById(R.id.relative_layout_handles);
        relativeLayoutHandles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent woodenIntent = new Intent(getBaseContext(), ListActivity.class);
                woodenIntent.putExtra("categoryName", "doorHandle");
                startActivity(woodenIntent);
            }
        });
    }

}