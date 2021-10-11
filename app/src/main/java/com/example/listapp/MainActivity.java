package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

}