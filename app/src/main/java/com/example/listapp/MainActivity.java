package com.example.listapp;

import androidx.annotation.NonNull;
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
import android.graphics.Color;
import android.os.Bundle;

import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;

import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listapp.adapters.ItemAdapter;
import com.example.listapp.adapters.PanelViewAdapter;
import com.example.listapp.model.DataCallback;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.IDataLoader;
import com.example.listapp.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PanelViewAdapter.OnItemClickListener, ItemAdapter.OnItemClickListener{

    private class ViewHolder {
        //The views in main activity go here
        RelativeLayout wooden_category_button;
        RelativeLayout metal_category_button;
        RelativeLayout glass_category_button;
        RelativeLayout handle_category_button;
        BottomNavigationView bottomNavigationView;
        RecyclerView panel_recycler_view;

        public ViewHolder() {
            //The elements common among all items assigned here
            wooden_category_button = findViewById(R.id.relative_layout_wooden);
            metal_category_button = findViewById(R.id.relative_layout_metal);
            glass_category_button = findViewById(R.id.relative_layout_glass);
            handle_category_button = findViewById(R.id.relative_layout_handles);
            panel_recycler_view = findViewById(R.id.panelRecyclerView);
            bottomNavigationView = findViewById(R.id.bottom_navigation_menu);
        }
    }

    //variables
    private List<Item> panelItems = new ArrayList<>();
    IDataLoader dataLoader = new DataLoader();

    ViewHolder mainActivityVH;

    boolean panelViewDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("On creation");
//        initPanelItems();
        Log.d("afterInit", "On creation after initPanelItems()");

        mainActivityVH = new ViewHolder();

        mainActivityVH.panel_recycler_view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (panelViewDone) {
                    mainActivityVH.panel_recycler_view.getViewTreeObserver().removeOnPreDrawListener(this);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    Animation panelViewAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.panel_view_slide_from_right);
                    mainActivityVH.wooden_category_button.startAnimation(animation);
                    mainActivityVH.metal_category_button.startAnimation(animation);
                    mainActivityVH.glass_category_button.startAnimation(animation);
                    mainActivityVH.handle_category_button.startAnimation(animation);
                    findViewById(R.id.panelRecyclerView).startAnimation(panelViewAnimation);
                    return true;
                } else {
                    return false;
                }
            }
        });

        initPanelRecyclerView();

        mainActivityVH.wooden_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "wooden");
                startActivity(listActivity);
//                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
//                        findViewById(R.id.custom_toolbar), "listActivityTransition"); //new Pair<>(view, "topPicksImageTransition")
//
//                ActivityCompat.startActivity(MainActivity.this, listActivity, activityOptions.toBundle());
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        mainActivityVH.metal_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "metallic");
                startActivity(listActivity);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        mainActivityVH.glass_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "glass");
                startActivity(listActivity);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        mainActivityVH.handle_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "handle");
                startActivity(listActivity);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);


        mainActivityVH.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home_page) {

                    return true;
                } else if (item.getItemId() == R.id.favourites_page) {
                    Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                    listActivity.putExtra("type", "favourites");
                    startActivity(listActivity);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    return true;
                }
                return false;
            }
        });

//        CardView cardView = (CardView) findViewById(R.id.card_view_1);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("Details activity clicked");
//                Intent detailIntent = new Intent(getBaseContext(), DetailsActivity.class);
//                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
//                        new Pair<>(view.findViewById(R.id.image_1), "topPicksImageTransition"));
//                ActivityCompat.startActivity(MainActivity.this, detailIntent, activityOptions.toBundle());
////                startActivity(detailIntent);
//            }
//        });
    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        mainActivityVH.bottomNavigationView.setSelectedItemId(R.id.home_page);
        initPanelRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search an item!");
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", s);
                startActivity(listActivity);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
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
        List<Integer> temp = new ArrayList<>();
        temp.add(5);
        temp.add(17);
        temp.add(12);
        temp.add(37);
        temp.add(24);

        dataLoader.getItemsByID(temp, new DataCallback() {
            @Override
            public void dataListCallback(List<Item> itemlist) {
                for (Item i : itemlist) {
                    Log.d("testname", i.getName().toString());
                    Log.d("testid", Integer.toString(i.getId()));
                    Log.d("testViewCount", Integer.toString(i.getViewCount()));
                    Log.d("testFirestoreID", i.getFirestoreID());
                    dataLoader.persistData(i);
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
        mainActivityVH.panel_recycler_view.setLayoutManager(linearLayoutManager);

        DataLoader dataLoader = new DataLoader();
        dataLoader.sortItemListByViewCount(new DataCallback() {
            @Override
            public void dataListCallback(List<Item> itemList) {
                PanelViewAdapter panelViewAdapter = new PanelViewAdapter(itemList, MainActivity.this, MainActivity.this);
                mainActivityVH.panel_recycler_view.setAdapter(panelViewAdapter);
                panelViewDone = true;
            }

            @Override
            public void itemCallback(Item item) {
                // No implementation needed
            }
        });
    }

    @Override
    public void onItemClick(int itemId, View view) {
        Log.d("CREATION", "onNoteClick: Clicked item id " + itemId);
        Intent listActivity = new Intent(getBaseContext(), DetailsActivity.class);
        listActivity.putExtra("id", "" + itemId);
//        startActivity(listActivity);

                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                view, "topPicksImageTransition"); //new Pair<>(view, "topPicksImageTransition")
        ActivityCompat.startActivity(this, listActivity, activityOptions.toBundle());
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