package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.viewpager.widget.ViewPager;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listapp.adapters.ImageAdapter;
import com.example.listapp.model.DataCallback;
import com.example.listapp.model.DataLoader;
import com.example.listapp.model.Door;
import com.example.listapp.model.IDataLoader;
import com.example.listapp.model.Item;
import com.example.listapp.model.WoodenDoor;

import java.util.ArrayList;
import java.util.List;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.util.Log;

public class DetailsActivity extends AppCompatActivity {

    private class ViewHolder {
        //The views in details activity go here
        TextView itemName;
        TextView description;
        TextView itemSpecification;
        TextView price;

        ViewPager viewPager;

        public ViewHolder() {
            //The elements common among all items assigned here
            itemName = findViewById(R.id.item_name);
            description = findViewById(R.id.item_description_text);
            itemSpecification = findViewById(R.id.item_specification_text);
            price = findViewById(R.id.item_price);

            viewPager = findViewById(R.id.imageViewPager);
        }
    }

    Item itemSelected;
    IDataLoader dataLoader = new DataLoader();

    String nameString = "";

    boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        postponeEnterTransition();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        Log.d("detailsActivity", "Details activity launched");
        System.out.println("Details activity launched");

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_details);
        setSupportActionBar(toolbar);

        ViewHolder detailsActivityVh = new ViewHolder();

        Fade fade = new Fade();
        fade.excludeTarget(R.id.custom_toolbar_details, true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        Intent thisIntent = getIntent();
        String itemId = thisIntent.getStringExtra("id");
        Toast.makeText(this, "Showing item with id " + itemId, Toast.LENGTH_LONG).show();

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
                    for (String s : itemSelected.getName()) {
                        nameString += s + " ";
                    }
                    List<Long> dimensions = itemSelected.getDimensions();
                    String dimensionString = dimensions.get(0) + " x " + dimensions.get(1) + " x " +
                            dimensions.get(2) + " (mm)";

                    detailsActivityVh.itemName.setText(nameString);
                    detailsActivityVh.description.setText(itemSelected.getDescription());
                    detailsActivityVh.price.setText("$" + String.format("%.2f", itemSelected.getPrice()));
                    detailsActivityVh.itemSpecification.setText(dimensionString);

                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.description_relative_layout);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_details_view);
                    relativeLayout.startAnimation(animation);

                    ViewCompat.setTransitionName(findViewById(R.id.imageViewPager), "topPicksImageTransition");

                    ViewPager viewPager = findViewById(R.id.imageViewPager);
                    ImageAdapter adapter = new ImageAdapter(DetailsActivity.this, itemSelected.getImage());
                    viewPager.setAdapter(adapter);

                    done = true;


                }
            });
        } else {
            createDefaultItem();
        }

        detailsActivityVh.viewPager.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (done) {
                    detailsActivityVh.viewPager.getViewTreeObserver().removeOnPreDrawListener(this);

//                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//                    Animation panelViewAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_right);
//                    mainActivityVH.wooden_category_button.startAnimation(animation);
//                    mainActivityVH.metal_category_button.startAnimation(animation);
//                    mainActivityVH.glass_category_button.startAnimation(animation);
//                    mainActivityVH.handle_category_button.startAnimation(animation);
//                    findViewById(R.id.panelRecyclerView).startAnimation(panelViewAnimation);
                    startPostponedEnterTransition();
                    return true;
                } else {
                    return false;
                }
            }
        });




    }

    private void createDefaultItem() {
        List<Long> dimensions = new ArrayList<>();
        dimensions.add(new Long(2355));
        dimensions.add(new Long (566));
        dimensions.add(new Long(36));

        List<String> name = new ArrayList<>();
        name.add("Authentic");
        name.add("Gloss");
        name.add("Finished");
        name.add("Gold");
        name.add("Doorknob");

        List<String> colour = new ArrayList<>();
        colour.add("#000000");
        colour.add("#000000");
        colour.add("#000000");

        List<String> images = new ArrayList<>();
        images.add("handle3_1");
        images.add("handle3_2");
        images.add("handle3_3");

        itemSelected = new WoodenDoor(1, 560, 43, 50.50f, dimensions, name,
                "dsafk sadflkd dslfka dsa", colour, images);

        itemSelected = new WoodenDoor(1, 560, 43, 50.50f, dimensions, name,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et " +
                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
                        "ligula porta id. Curabitur libero ligula, pulvinar ac convallis nec, " +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et " +
                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
                        "ligula porta id. Curabitur libero\n\n ligula, pulvinar ac convallis nec, " +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et " +
                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
                        "ligula porta id. Curabitur libero ligula, pulvinar ac convallis nec, " +
                        "Lorem ipsum dolor sit amet, consectetur adipisc\n\ning elit. Mauris et " +
                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
                        "ligula porta id. Curabitur libero ligula, pulvinar ac convallis nec, " +
                        "hendrerit a lacus.", colour, images);
    }
}