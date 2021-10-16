package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionManager;

import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listapp.adapters.PanelViewAdapter;
import com.example.listapp.model.DataCallback;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder {
        //The views in main activity go here
        RelativeLayout wooden_category_button;
        RelativeLayout metal_category_button;
        RelativeLayout glass_category_button;
        RelativeLayout handle_category_button;

        public ViewHolder() {
            //The elements common among all items assigned here
            wooden_category_button = findViewById(R.id.relative_layout_wooden);
            metal_category_button = findViewById(R.id.relative_layout_metal);
            glass_category_button = findViewById(R.id.relative_layout_glass);
            handle_category_button = findViewById(R.id.relative_layout_handles);
        }
    }

    //variables
    private List<Item> panelItems = new ArrayList<>();
    DataLoader dataLoader = new DataLoader();

    ViewHolder mainActivityVH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("On creation");
        initPanelItems();
        Log.d("afterInit", "On creation after initPanelItems()");

        mainActivityVH = new ViewHolder();

        mainActivityVH.wooden_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "wooden");
                startActivity(listActivity);
            }
        });

        mainActivityVH.metal_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "metallic");
                startActivity(listActivity);
            }
        });

        mainActivityVH.glass_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "glass");
                startActivity(listActivity);
            }
        });

        mainActivityVH.handle_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "handle");
                startActivity(listActivity);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        CardView cardView = (CardView) findViewById(R.id.card_view_1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Details activity clicked");
                Intent detailIntent = new Intent(getBaseContext(), DetailsActivity.class);
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                        new Pair<>(view.findViewById(R.id.image_1), "topPicksImageTransition"));
                ActivityCompat.startActivity(MainActivity.this, detailIntent, activityOptions.toBundle());
//                startActivity(detailIntent);
            }
        });
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search an item!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", s);
                startActivity(listActivity);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    private void initPanelItems() {
        // TODO: 05/10/2021 Requires implementation of code to retrieve data from Firestore DB and helper functions to sort and etc, in order to populate panelItems list.
        dataLoader.getItemsByCriteria("metallic", new DataCallback() {
            @Override
            public void dataListCallback(List<Item> itemlist) {
                for (Item i : itemlist) {
                    Log.d("jchename", i.getName().toString());
                    Log.d("jcheid", Integer.toString(i.getId()));
                    Log.d("jcheprice", Float.toString(i.getPrice()));
                    Log.d("jchedimensions", i.getDimensions().toString());
                    Log.d("jchedescription", i.getDescription());
                }
            }

            @Override
            public void itemCallback(Item i) {

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

    /**
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
                woodenIntent.putExtra("categoryName", "metallic");
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
    }**/

}