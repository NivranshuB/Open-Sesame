package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.listapp.adapters.ItemAdapter;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.IDataLoader;

public class ListActivity extends AppCompatActivity {

    ItemAdapter itemAdapter;
    RecyclerView recyclerView;
    IDataLoader dataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
        dataLoader = new DataLoader();
        dataLoader.initialiseData();

        Intent intent = getIntent();
        itemAdapter = new ItemAdapter(this, R.layout.item_square, dataLoader.getItemsByCriteria(intent.getStringExtra("categoryName")));

        recyclerView.setAdapter(itemAdapter);

    }
}