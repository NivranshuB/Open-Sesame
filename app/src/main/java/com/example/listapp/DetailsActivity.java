package com.example.listapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listapp.adapters.ImageAdapter;
import com.example.listapp.model.DataCallback;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.Door;
import com.example.listapp.model.DoorHandle;
import com.example.listapp.model.IDataLoader;
import com.example.listapp.model.Item;
import com.example.listapp.model.WoodenDoor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.util.Log;
import android.widget.ToggleButton;

public class DetailsActivity extends AppCompatActivity {

    private class ViewHolder {
        //The views in details activity go here
        TextView itemName;
        TextView description;
        TextView itemSpecification;
        TextView weight;
        TextView viewCount;
        TextView price;
        TextView category;

        ViewPager viewPager;
        Toolbar toolbar;
        ToggleButton favouritesToggle;
        RelativeLayout descriptionRelativeLayout;

        public ViewHolder() {
            //The elements common among all items assigned here
            itemName = findViewById(R.id.item_name);
            description = findViewById(R.id.item_description_text);
            itemSpecification = findViewById(R.id.item_specification_text);
            weight = findViewById(R.id.weight_text);
            viewCount = findViewById(R.id.view_count_text);
            category = findViewById(R.id.category_text);
            price = findViewById(R.id.item_price);
            viewPager = findViewById(R.id.imageViewPager);
            toolbar = findViewById(R.id.custom_toolbar_details);
            favouritesToggle = findViewById(R.id.details_favourite_icon);
            descriptionRelativeLayout = findViewById(R.id.description_relative_layout);
        }
    }

    Item itemSelected;
    IDataLoader dataLoader = new DataLoader();
    ViewHolder detailsActivityVH;

    String nameString = "";

    boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        postponeEnterTransition();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsActivityVH = new ViewHolder();

        detailsActivityVH.toolbar.setTitle("");
        setSupportActionBar(detailsActivityVH.toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent thisIntent = getIntent();
        String itemId = thisIntent.getStringExtra("id");

        detailsActivityVH.viewPager.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (done) {
                    detailsActivityVH.viewPager.getViewTreeObserver().removeOnPreDrawListener(this);
                    startPostponedEnterTransition();
                    return true;
                } else {
                    return false;
                }
            }
        });

        createFavourites(itemId);

        if (itemId != null) {
            int id = Integer.parseInt(itemId);
            dataLoader.getItemByID(id, new DataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {
                    // No implementation needed
                }

                @Override
                public void itemCallback(Item item) {
                    itemSelected = item;
                    itemSelected.incrementViewCount();
                    dataLoader.persistData(itemSelected);

                    for (String s : itemSelected.getName()) {
                        nameString += s + " ";
                    }

                    if (itemSelected.getClass() != DoorHandle.class) {
                        List<Long> dimensions = itemSelected.getDimensions();
                        String dimensionString = dimensions.get(0) + " x " + dimensions.get(1) + " x " +
                                dimensions.get(2) + " (mm)";
                        detailsActivityVH.itemSpecification.setText(dimensionString);
                    } else {
                        if (itemSelected.getLockable()) {
                            detailsActivityVH.itemSpecification.setText("Lockable: Yes");
                        } else {
                            detailsActivityVH.itemSpecification.setText("Lockable: No");
                        }
                    }

                    detailsActivityVH.weight.setText("Weight: " + itemSelected.getWeight() + "kg");
                    detailsActivityVH.viewCount.setText(String.valueOf(itemSelected.getViewCount()));

                    detailsActivityVH.itemName.setText(nameString);
                    detailsActivityVH.description.setText(itemSelected.getDescription());
                    detailsActivityVH.price.setText("$" + String.format("%.2f", itemSelected.getPrice()));
                    detailsActivityVH.category.setText("Category: " + itemSelected.getCategories().get(0));

                    createTransition();

                    ImageAdapter adapter = new ImageAdapter(DetailsActivity.this, itemSelected.getImage());
                    detailsActivityVH.viewPager.setAdapter(adapter);
                    startPostponedEnterTransition();
                    done = true;
                }
            });
        }
    }

    private void createFavourites(String itemId) {
        SharedPreferences sharedPreferences = getSharedPreferences("favourites", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Drawable notToggledImage = getDrawable(R.drawable.ic_baseline_favorite_border_24);
        Drawable toggledImage = getDrawable(R.drawable.ic_baseline_favorite_24);
        detailsActivityVH.favouritesToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    detailsActivityVH.favouritesToggle.setBackground(toggledImage);
                    editor.putString(itemId, itemId);

                } else {
                    detailsActivityVH.favouritesToggle.setBackground(notToggledImage);
                    editor.remove(itemId);
                }
                editor.commit();
            }
        });

        if (sharedPreferences.getString(itemId, null) != null) {
            detailsActivityVH.favouritesToggle.setChecked(true);
            detailsActivityVH.favouritesToggle.setBackground(toggledImage);
        }
    }

    private void createTransition() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_details_view);
        detailsActivityVH.descriptionRelativeLayout.startAnimation(animation);

        ViewCompat.setTransitionName(detailsActivityVH.viewPager, ListActivity.TOP_PICKS_IMAGE_TRANSITION);
    }

    @Override
    public void onBackPressed() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_details_activity);
        detailsActivityVH.descriptionRelativeLayout.startAnimation(animation);
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_details_activity);
                detailsActivityVH.descriptionRelativeLayout.startAnimation(animation);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    }
