package com.example.listapp;

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
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

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
//        postponeEnterTransition();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
        //dataLoader = new DataLoader();
        //dataLoader.initialiseData();

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("type");

        DataLoader dataLoader = new DataLoader();
        if (!(categoryName == null) && categoryName.equals("handle")) {
            Toolbar toolbar = findViewById(R.id.custom_toolbar_list);
            toolbar.setBackgroundColor(getResources().getColor(R.color.light_green));
            View view = findViewById(R.id.rounded_corners);
            view.setBackgroundResource(R.drawable.light_green_rounded_corners_background);
            getWindow().setStatusBarColor(getResources().getColor(R.color.light_green));
//            itemAdapter = new ItemAdapter(this, R.layout.door_handle_square, dataLoader.getItemsByCriteria(categoryName));
            dataLoader.getItemsByCriteria(categoryName, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {


                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.door_handle_square,
                            itemList, ListActivity.this);
                    recyclerView.setAdapter(itemAdapter);
//                    startPostponedEnterTransition();
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
        } else {
            String formattedString = capitaliseWord(categoryName);
            dataLoader.getItemsByName(formattedString, new DataCallback() {
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
//        startActivity(listActivity);
//                startActivity(detailIntent);
    }

    public static String capitaliseWord(String str){
        str = str.toLowerCase();
        String words[]=str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}