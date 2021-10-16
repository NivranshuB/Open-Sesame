package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.listapp.adapters.ItemAdapter;
import com.example.listapp.model.DataCallback;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.IDataLoader;
import com.example.listapp.model.Item;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

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
        String categoryName = intent.getStringExtra("type");
        if (categoryName == null) {
            return;
        }
        if (categoryName.equals("doorHandle")) {
//            itemAdapter = new ItemAdapter(this, R.layout.door_handle_square, dataLoader.getItemsByCriteria(categoryName));
        } else {
            DataLoader dataLoader = new DataLoader();
            dataLoader.getItemsByCriteria(categoryName, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {
                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square,
                            itemList, ListActivity.this);
                    recyclerView.setAdapter(itemAdapter);
                }

                @Override
                public void itemCallback(Item item) {
                    // No implementation needed
                }
            });

        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_list);
        toolbar.setTitle(categoryName.toUpperCase());

    }

    @Override
    public void onItemClick(int itemId) {
        Log.d("CREATION","onNoteClick: Clicked item id " + itemId);
        Intent listActivity = new Intent(getBaseContext(), DetailsActivity.class);
        listActivity.putExtra("id", "" + itemId);
        startActivity(listActivity);
    }
}