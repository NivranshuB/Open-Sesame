package com.example.listapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.listapp.adapters.ItemAdapter;
import com.example.listapp.adapters.PanelViewAdapter;
import com.example.listapp.data.IDataCallback;
import com.example.listapp.data.DataLoader;
import com.example.listapp.data.IDataLoader;
import com.example.listapp.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for creating the MainActivity page of our application. This class also
 * creates the most viewed panel using the PanelViewAdapter to create the horizontal recycler view.
 * The MainActivity also has a bottom bar that allows you to toggle from this page to the favourites
 * page and vice versa.
 *
 * This class has an inner view holder class that holds the view of activity_main.xml.
 */
public class MainActivity extends AppCompatActivity implements PanelViewAdapter.OnItemClickListener,
        ItemAdapter.OnItemClickListener {

    //variables
    private List<Item> panelItems = new ArrayList<>();
    IDataLoader dataLoader = DataLoader.getDataLoader();

    ViewHolder mainActivityVH;

    boolean panelViewDone = false;

    /**
     * Inner class that holds the different views of the MainActivity page.
     */
    private class ViewHolder {
        //The views in main activity go here
        RelativeLayout wooden_category_button;
        RelativeLayout metal_category_button;
        RelativeLayout glass_category_button;
        RelativeLayout handle_category_button;
        BottomNavigationView bottomNavigationView;
        RecyclerView panel_recycler_view;
        Toolbar toolbar;

        public ViewHolder() {
            //The elements common among all items assigned here
            wooden_category_button = findViewById(R.id.relative_layout_wooden);
            metal_category_button = findViewById(R.id.relative_layout_metal);
            glass_category_button = findViewById(R.id.relative_layout_glass);
            handle_category_button = findViewById(R.id.relative_layout_handles);
            panel_recycler_view = findViewById(R.id.panelRecyclerView);
            bottomNavigationView = findViewById(R.id.bottom_navigation_menu);
            toolbar = findViewById(R.id.custom_toolbar);
        }
    }

    /**
     * Overriding method which is called during the creation of the MainActivity page of our
     * application. This method also instantiates the most viewed panel sort in descending order
     * with respect to item views.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityVH = new ViewHolder();

        startAnimation();

        initPanelRecyclerView();

        createCategoryClickListeners();

        setSupportActionBar(mainActivityVH.toolbar);

        initBottomNavigationView();

    }

    /**
     * This method is used to ensure that the most viewed panel refreshes when a user clicks back
     * from another activity back to the MainActivity page.
     */
    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        mainActivityVH.bottomNavigationView.setSelectedItemId(R.id.home_page);
        initPanelRecyclerView();
    }

    /**
     * Set all the UI components of the action bar of MainActivity. This includes the app name, the
     * app logo and the search view.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search an item!");
        searchView.setIconifiedByDefault(false);

        //Set on click listener that listens to search queries and then passes this information to
        //list activity
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

    /**
     * Links the interactive components of the MainActivity page to the intended animation such that
     * these components will transition as designed to.
     */
    private void startAnimation() {
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
                    mainActivityVH.panel_recycler_view.startAnimation(panelViewAnimation);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    /**
     * Initialises the panel recycler view by passing a list of items sorted by view count to a
     * new PanelViewAdapter instance. The sorted list of items is retrieved through the use of the
     * DataLoader singleton instance.
     */
    private void initPanelRecyclerView() {
        dataLoader = DataLoader.getDataLoader();
        dataLoader.sortItemListByViewCount(new IDataCallback() {
            @Override
            public void dataListCallback(List<Item> itemList) {
                PanelViewAdapter panelViewAdapter = new PanelViewAdapter(itemList,
                        MainActivity.this, MainActivity.this);
                mainActivityVH.panel_recycler_view.setAdapter(panelViewAdapter);
                panelViewDone = true;
            }

            @Override
            public void itemCallback(Item item) {
                // No implementation needed
            }
        });
    }

    /**
     * Initialises the bottom navigation view of the MainActivity page. This bar contains two
     * buttons, the favourites button and the home button.
     */
    private void initBottomNavigationView() {
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
    }

    /**
     * This method reacts to the click of an item in the most viewed panel and then opens up the
     * DetailsActivity page for that particular item.
     * @param itemId
     * @param view
     */
    @Override
    public void onItemClick(int itemId, View view) {
        Intent listActivity = new Intent(getBaseContext(), DetailsActivity.class);
        listActivity.putExtra("id", "" + itemId);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                view, ListActivity.TOP_PICKS_IMAGE_TRANSITION);
        ActivityCompat.startActivity(this, listActivity, activityOptions.toBundle());
    }

    /**
     * This method creates the category listeners for each of the four category buttons in the
     * MainActivity page. Clicking on any of the category buttons takes the user to the relevant
     * category ListActivity page.
     */
    private void createCategoryClickListeners() {
        mainActivityVH.wooden_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                listActivity.putExtra("type", "wooden");
                startActivity(listActivity);
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
    }
}