package com.example.listapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listapp.adapters.ItemAdapter;
import com.example.listapp.model.DataCallback;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.IDataLoader;
import com.example.listapp.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

    ItemAdapter itemAdapter;
    RecyclerView recyclerView;
    IDataLoader dataLoader;
    SharedPreferences sharedPreferences;
    Intent intent; //check

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        sharedPreferences = getSharedPreferences("favourites", MODE_PRIVATE);

        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
        findViewById(R.id.no_results_found).setVisibility(View.INVISIBLE);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_menu);

        intent = getIntent(); //check
        String categoryName = intent.getStringExtra("type");
        dataLoader = new DataLoader();
        if (!(categoryName == null) && categoryName.equals("handle")) {
            Toolbar toolbar = findViewById(R.id.custom_toolbar_list);
            toolbar.setBackgroundColor(getResources().getColor(R.color.light_green));
            View view = findViewById(R.id.rounded_corners);
            view.setBackgroundResource(R.drawable.light_green_rounded_corners_background);
            getWindow().setStatusBarColor(getResources().getColor(R.color.light_green));
            dataLoader.getItemsByCriteria(categoryName, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {


                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.door_handle_square,
                            itemList, ListActivity.this);
                    recyclerView.setAdapter(itemAdapter);
                    ViewCompat.setTransitionName(findViewById(R.id.custom_toolbar_list), "listActivityTransition");
                }

                @Override
                public void itemCallback(Item item) {
                    // No implementation needed
                }
            });
        } else if (!(categoryName == null) && (categoryName.equals("wooden") ||
                categoryName.equals("glass") || categoryName.equals("metallic"))) {

            int colorId = 0;
            int cornersId = 0;
            if (categoryName.equals("wooden")) {
                colorId = R.color.brown;
                cornersId = R.drawable.brown_rounded_corners_background;
            } else if (categoryName.equals("metallic")) {
                colorId = R.color.grey;
                cornersId = R.drawable.grey_rounded_corners_background;
            } else if (categoryName.equals("glass")) {
                colorId = R.color.light_blue;
                cornersId = R.drawable.light_blue_rounded_corners_background;
            }
            Toolbar toolbar = findViewById(R.id.custom_toolbar_list);
            toolbar.setBackgroundColor(getResources().getColor(colorId));
            View view = findViewById(R.id.rounded_corners);
            view.setBackgroundResource(cornersId);
            getWindow().setStatusBarColor(getResources().getColor(colorId));

            dataLoader.getItemsByCriteria(categoryName, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {
                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square,
                            itemList, ListActivity.this);
                    recyclerView.setAdapter(itemAdapter);
                    startPostponedEnterTransition();
                }

                @Override
                public void itemCallback(Item item) {
                    // No implementation needed
                }
            });
        } else if (!(categoryName == null) && categoryName.equals("favourites")) {
//            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_menu);

            bottomNavigationView.setVisibility(View.VISIBLE);
            bottomNavigationView.setSelectedItemId(R.id.favourites_page);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.home_page) {
                        Intent mainActivity = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(mainActivity);
                        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                        return true;
                    }
                    return false;
                }
            });


            initFavouritesList();

            Button clearButton = (Button) findViewById(R.id.clear_button);
            clearButton.setVisibility(View.VISIBLE);
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Cleared favourites", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    initFavouritesList();
                }
            });


        } else {
            String formattedString = capitaliseWord(categoryName);
            dataLoader.getItemsByName(formattedString, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {
                    Log.d("Check", "ItemList size is " + itemList.size());
                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square,
                            itemList, ListActivity.this);
                    recyclerView.setAdapter(itemAdapter);
                    if (itemList.size() < 1 || itemList == null) {
                        findViewById(R.id.no_results_found).setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void itemCallback(Item item) {
                    // No implementation needed
                }
            });

        }


        if (!(categoryName == null)) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_list);
            toolbar.setTitle(categoryName.toUpperCase());
            setSupportActionBar(toolbar);
            ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public void onItemClick(int itemId, View view) {
        Log.d("CREATION", "onNoteClick: Clicked item id " + itemId);
        Intent listActivity = new Intent(getBaseContext(), DetailsActivity.class);
        listActivity.putExtra("id", "" + itemId);
//        startActivity(listActivity);


        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair<>(view, "topPicksImageTransition"));

        ActivityCompat.startActivity(this, listActivity, activityOptions.toBundle());

    }

    public static String capitaliseWord(String str) {
        str = str.toLowerCase();
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                bottomNavigationView.setSelectedItemId(R.id.home_page);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initFavouritesList() {
        SharedPreferences sharedPreferences = getSharedPreferences("favourites", MODE_PRIVATE);
        Map<String, String> map = (Map<String, String>) sharedPreferences.getAll();
        List<Integer> favouriteIDList = new ArrayList<>();
        int count = 0;
        if (map.keySet().size() > 0) {
            for (String s : map.keySet()) {
                if (count < 10) {
                    favouriteIDList.add(Integer.parseInt(s));
                    count++;
                } else {
                    break;
                }
            }
            dataLoader.getItemsByID(favouriteIDList, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {
                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square, itemList, ListActivity.this);
                    recyclerView.setAdapter(itemAdapter);
                }
                @Override
                public void itemCallback(Item item) {
                    // not needed
                }
            });
        } else {
            List<Item> emptyList = new ArrayList<>();
            itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square, emptyList, ListActivity.this);
            recyclerView.setAdapter(itemAdapter);
        }
    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        String categoryName = intent.getStringExtra("type");

        if (!(categoryName == null) && categoryName.equals("favourites")) {
            initFavouritesList();
        }
    }

}