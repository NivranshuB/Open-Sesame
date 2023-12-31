package com.example.listapp;

import static com.example.listapp.data.TextFormatting.formatDimensions;
import static com.example.listapp.data.TextFormatting.mergeStringList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.listapp.adapters.ImageAdapter;
import com.example.listapp.data.IDataCallback;
import com.example.listapp.data.DataLoader;
import com.example.listapp.model.DoorHandle;
import com.example.listapp.data.IDataLoader;
import com.example.listapp.model.Item;

import java.util.List;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ToggleButton;

/**
 * This class is responsible for creating the DetailsActivity page of our application. The
 * DetailsActivity page is used display all the attributes of a specific item of our application.
 *
 * This class has an inner view holder class that holds the view of activity_details.xml.
 */
public class DetailsActivity extends AppCompatActivity {

    Item itemSelected;
    IDataLoader dataLoader = DataLoader.getDataLoader();
    ViewHolder detailsActivityVH;

    String nameString = "";

    boolean done = false;

    /**
     * Inner class that holds the different views of the DetailsActivity page.
     */
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

    /**
     * Overriding method which is called during the creation of the DetailsActivity page of our
     * application.
     * @param savedInstanceState
     */
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
            dataLoader.getItemByID(id, new IDataCallback() {
                @Override
                public void dataListCallback(List<Item> itemList) {
                    // No implementation needed
                }

                @Override
                public void itemCallback(Item item) {
                    itemSelected = item;
                    itemSelected.incrementViewCount();
                    dataLoader.persistData(itemSelected);

                    nameString = mergeStringList(item.getName());

                    if (itemSelected.getClass() != DoorHandle.class) {
                        detailsActivityVH.itemSpecification.setText(formatDimensions(itemSelected.getDimensions()));
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

    /**
     * Create the favourite toggle button at the top right corner of the details activity. Setting
     * this button adds this item to favourites while unsetting it removes it from favourites.
     * @param itemId
     */
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

    /**
     * Add transition to the DetailsActivity page.
     */
    private void createTransition() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_details_view);
        detailsActivityVH.descriptionRelativeLayout.startAnimation(animation);

        ViewCompat.setTransitionName(detailsActivityVH.viewPager, ListActivity.TOP_PICKS_IMAGE_TRANSITION);
    }

    /**
     * Add the designed transition to when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_details_activity);
        detailsActivityVH.descriptionRelativeLayout.startAnimation(animation);
        super.onBackPressed();

    }

    /**
     * Add transitions related to the the action bar components of the DetailsActivity page.
     * @param item
     * @return
     */
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
