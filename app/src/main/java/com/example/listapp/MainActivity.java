package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.provider.Settings;

import com.example.listapp.adapters.PanelViewAdapter;
import com.example.listapp.model.DataCallback;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //variables
    private List<Item> panelItems = new ArrayList<>();
    DataLoader dataLoader = new DataLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("On creation");
        initPanelItems();
        Log.d("afterInit", "On creation after initPanelItems()");

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout_wooden);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("List activity clicked");
                Intent woodenIntent = new Intent(getBaseContext(), ListActivity.class);
                startActivity(woodenIntent);
            }
        });

        CardView cardView = (CardView) findViewById(R.id.card_view_1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Details activity clicked");
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
        Item i = dataLoader.getItemByID(27, new DataCallback() {
            @Override
            public void dataListCallback(List<Item> itemList) {
                //No implementation required
            }

            @Override
            public void itemCallback(Item i) {
                Log.d("jchename", i.getName().toString());
                Log.d("jcheid", Integer.toString(i.getId()));
                Log.d("jcheprice", Float.toString(i.getPrice()));
                Log.d("jchedimensions", i.getDimensions().toString());
                Log.d("jchedescription", i.getDescription());
            }
        });
        Log.d("initialise", "initPanelItems running");

    }


    private void initPanelRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView panelRecyclerView = findViewById(R.id.panelRecyclerView);
        panelRecyclerView.setLayoutManager(linearLayoutManager);
        PanelViewAdapter panelViewAdapter = new PanelViewAdapter(panelItems, this);
        panelRecyclerView.setAdapter(panelViewAdapter);
    }

}