package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Transition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
        postponeEnterTransition();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
        //dataLoader = new DataLoader();
        //dataLoader.initialiseData();

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("type");

        DataLoader dataLoader = new DataLoader();
        if (!(categoryName == null) && categoryName.equals("handle")) {
//            itemAdapter = new ItemAdapter(this, R.layout.door_handle_square, dataLoader.getItemsByCriteria(categoryName));
            dataLoader.getItemsByCriteria(categoryName, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {


                    itemAdapter = new ItemAdapter(ListActivity.this, R.layout.door_handle_square,
                            itemList, ListActivity.this);
                    recyclerView.setAdapter(itemAdapter);
                    startPostponedEnterTransition();
                    ViewCompat.setTransitionName(findViewById(R.id.custom_toolbar_list), "listActivityTransition");
                }

                @Override
                public void itemCallback(Item item) {
                    // No implementation needed
                }
            });
        } else {
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

        }


        if (!(categoryName == null)) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_list);
            toolbar.setTitle(categoryName.toUpperCase());
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
}