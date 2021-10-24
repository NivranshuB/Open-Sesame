package com.example.listapp;

import static com.example.listapp.data.TextFormatting.capitaliseWord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listapp.adapters.ItemAdapter;
import com.example.listapp.data.IDataCallback;
import com.example.listapp.data.DataLoader;
import com.example.listapp.data.IDataLoader;
import com.example.listapp.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for creating the ListActivity page of our application. The ListActivity
 * page is used for the different category list views, the search view as well as the favourites
 * view.
 *
 * This class has an inner view holder class that holds the view of activity_list.xml.
 */
public class ListActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

    ViewHolder listActivityVH;

    ItemAdapter itemAdapter;
    IDataLoader dataLoader;
    SharedPreferences sharedPreferences;
    Intent intent; //check
    String categoryName;

    final static String TOP_PICKS_IMAGE_TRANSITION = "topPicksImageTransition";

    /**
     * Inner class that holds the different views of the ListActivity page.
     */
    private class ViewHolder {
        RecyclerView recyclerView;
        BottomNavigationView bottomNavigationView;
        LinearLayout noResultsFoundView;
        Toolbar toolbar;
        View roundCornersView;
        Button clearButton;

        public ViewHolder() {
            recyclerView = findViewById(R.id.grid_recycler_view);
            bottomNavigationView = findViewById(R.id.bottom_navigation_menu);
            noResultsFoundView = findViewById(R.id.no_results_found);
            toolbar = findViewById(R.id.custom_toolbar_list);
            roundCornersView = findViewById(R.id.rounded_corners);
            clearButton = findViewById(R.id.clear_button);
        }
    }

    /**
     * This overriding method which is called during the creation of the ListActivity page of our
     * application. This method also reads the intent extra data to decide which category list of
     * items it should create, or whether it should create the search list view or favourites list
     * view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        sharedPreferences = getSharedPreferences("favourites", MODE_PRIVATE);

        listActivityVH = new ViewHolder();

        listActivityVH.noResultsFoundView.setVisibility(View.INVISIBLE);

        intent = getIntent(); //check
        categoryName = intent.getStringExtra("type");
        dataLoader = DataLoader.getDataLoader();

        //Decide on which type of ListActivity to create by using the intents extra data passed
        if (!(categoryName == null) && categoryName.equals("handle")) {
            getHandles();
        } else if (!(categoryName == null) && (categoryName.equals("wooden") ||
                categoryName.equals("glass") || categoryName.equals("metallic"))) {
            getDoors();
        } else if (!(categoryName == null) && categoryName.equals("favourites")) {
            getFavourites();

        } else {
            getSearch();
        }

        if (!(categoryName == null)) {
            listActivityVH.toolbar.setTitle(categoryName.toUpperCase());
            setSupportActionBar(listActivityVH.toolbar);
            ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * This method gets all the handle type items through the use of the DataLoader singleton
     * instance.
     */
    private void getHandles() {
        listActivityVH.toolbar.setBackgroundColor(getResources().getColor(R.color.light_green));
        listActivityVH.roundCornersView.setBackgroundResource(R.drawable.light_green_rounded_corners_background);
        getWindow().setStatusBarColor(getResources().getColor(R.color.light_green));
        dataLoader.getItemsByCriteria(categoryName, new IDataCallback() {
            @Override
            public void dataListCallback(List<Item> itemList) {
                itemAdapter = new ItemAdapter(ListActivity.this, R.layout.door_handle_square,
                        itemList, ListActivity.this);
                listActivityVH.recyclerView.setAdapter(itemAdapter);
            }

            @Override
            public void itemCallback(Item item) {
                // No implementation needed
            }
        });
    }

    /**
     * This method gets all the door type items through the use of the DataLoader singleton
     * instance.
     */
    private void getDoors() {
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

        listActivityVH.toolbar.setBackgroundColor(getResources().getColor(colorId));
        listActivityVH.roundCornersView.setBackgroundResource(cornersId);
        getWindow().setStatusBarColor(getResources().getColor(colorId));

        dataLoader.getItemsByCriteria(categoryName, new IDataCallback() {
            @Override
            public void dataListCallback(List<Item> itemList) {
                itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square,
                        itemList, ListActivity.this);
                listActivityVH.recyclerView.setAdapter(itemAdapter);
                startPostponedEnterTransition();
            }

            @Override
            public void itemCallback(Item item) {
                // No implementation needed
            }
        });
    }

    /**
     * This method gets all the favourited items and uses these items to construct the list view
     * content for the favourites page.
     */
    private void getFavourites() {
        listActivityVH.bottomNavigationView.setVisibility(View.VISIBLE);
        listActivityVH.bottomNavigationView.setSelectedItemId(R.id.favourites_page);
        listActivityVH.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
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

        listActivityVH.clearButton.setVisibility(View.VISIBLE);
        listActivityVH.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cleared favourites", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                initFavouritesList();
            }
        });
    }

    /**
     * This method gets all the different types of items specified by the search string using the
     * DataLoader singleton instance.
     */
    private void getSearch() {
        String formattedString = capitaliseWord(categoryName);
        dataLoader.getItemsByName(formattedString, new IDataCallback() {
            @Override
            public void dataListCallback(List<Item> itemList) {
                itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square,
                        itemList, ListActivity.this);
                listActivityVH.recyclerView.setAdapter(itemAdapter);

                if (itemList.size() < 1 || itemList == null) {
                    listActivityVH.noResultsFoundView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void itemCallback(Item item) {
                // No implementation needed
            }
        });
    }

    /**
     * React as required when the back button in the action bar is pressed with the correct
     * transition animation.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    /**
     * React as required when an item is pressed with the correct transition animation. Switches the
     * application context to the DetailsActivity page of the item clicked.
     */
    @Override
    public void onItemClick(int itemId, View view) {
        Intent listActivity = new Intent(getBaseContext(), DetailsActivity.class);
        listActivity.putExtra("id", "" + itemId);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair<>(view, TOP_PICKS_IMAGE_TRANSITION));

        ActivityCompat.startActivity(this, listActivity, activityOptions.toBundle());

    }


    /**
     * React accordingly when components of the options bar are clicked.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                listActivityVH.bottomNavigationView.setSelectedItemId(R.id.home_page);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Initialises the users favourite list and populates this ListActivity page with their
     * favourited items.
     */
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
            dataLoader.getItemsByID(favouriteIDList, new IDataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {
                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square,
                            itemList, ListActivity.this);
                    listActivityVH.recyclerView.setAdapter(itemAdapter);
                }

                @Override
                public void itemCallback(Item item) {
                    // not needed
                }
            });
        } else {
            List<Item> emptyList = new ArrayList<>();
            itemAdapter = new ItemAdapter(ListActivity.this, R.layout.item_square, emptyList,
                    ListActivity.this);
            listActivityVH.recyclerView.setAdapter(itemAdapter);
        }
    }

    /**
     * When returning back from the DetailsActivity page to the ListActivity page ensure that the
     * screen refreshes.
     */
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