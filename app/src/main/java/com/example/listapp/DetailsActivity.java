package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.viewpager.widget.ViewPager;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
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
        TextView weight;
        TextView viewCount;
        TextView price;
        TextView category;

        ViewPager viewPager;

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

        Intent thisIntent = getIntent();
        String itemId = thisIntent.getStringExtra("id");

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
                    Log.d("Update", "Item's view count being updated has id: " + item.getName() + ":" + item.getId());
                    itemSelected.incrementViewCount();
                    dataLoader.persistData(itemSelected);

                    for (String s : itemSelected.getName()) {
                        nameString += s + " ";
                    }

                    if (itemSelected.getClass() != DoorHandle.class) {
                        List<Long> dimensions = itemSelected.getDimensions();
                        String dimensionString = dimensions.get(0) + " x " + dimensions.get(1) + " x " +
                                dimensions.get(2) + " (mm)";
                        detailsActivityVh.itemSpecification.setText(dimensionString);
                    } else {
                        if (itemSelected.getLockable()) {
                            detailsActivityVh.itemSpecification.setText("Handle has a completely " +
                                    "functioning locking mechanism");
                        } else {
                            detailsActivityVh.itemSpecification.setText("Handle does not contain a " +
                                    "locking mechanism");
                        }
                    }

                    detailsActivityVh.weight.setText("Weight: " + itemSelected.getWeight() + "kg");
                    detailsActivityVh.viewCount.setText(String.valueOf(itemSelected.getViewCount()));

                    detailsActivityVh.itemName.setText(nameString);
                    detailsActivityVh.description.setText(itemSelected.getDescription());
                    detailsActivityVh.price.setText("$" + String.format("%.2f", itemSelected.getPrice()));
                    detailsActivityVh.category.setText("Category: " + itemSelected.getCategories().get(0));


                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.description_relative_layout);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_details_view);
                    relativeLayout.startAnimation(animation);

                    ViewCompat.setTransitionName(findViewById(R.id.imageViewPager), "topPicksImageTransition");

                    ViewPager viewPager = findViewById(R.id.imageViewPager);
                    ImageAdapter adapter = new ImageAdapter(DetailsActivity.this, itemSelected.getImage());
                    viewPager.setAdapter(adapter);
                    startPostponedEnterTransition();
                    done = true;
                }
            });
        } else {
            //createDefaultItem();
        }

    }

//    private void createDefaultItem() {
//        List<Long> dimensions = new ArrayList<>();
//        dimensions.add(new Long(2355));
//        dimensions.add(new Long (566));
//        dimensions.add(new Long(36));
//
//        List<String> name = new ArrayList<>();
//        name.add("Authentic");
//        name.add("Gloss");
//        name.add("Finished");
//        name.add("Gold");
//        name.add("Doorknob");
//
//        List<String> colour = new ArrayList<>();
//        colour.add("#000000");
//        colour.add("#000000");
//        colour.add("#000000");
//
//        List<String> images = new ArrayList<>();
//        images.add("handle3_1");
//        images.add("handle3_2");
//        images.add("handle3_3");
//
//        itemSelected = new WoodenDoor(1, 560, 43, 50.50f, dimensions, name,
//                "dsafk sadflkd dslfka dsa", colour, images);
//
//        itemSelected = new WoodenDoor(1, 560, 43, 50.50f, dimensions, name,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et " +
//                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
//                        "ligula porta id. Curabitur libero ligula, pulvinar ac convallis nec, " +
//                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et " +
//                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
//                        "ligula porta id. Curabitur libero\n\n ligula, pulvinar ac convallis nec, " +
//                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et " +
//                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
//                        "ligula porta id. Curabitur libero ligula, pulvinar ac convallis nec, " +
//                        "Lorem ipsum dolor sit amet, consectetur adipisc\n\ning elit. Mauris et " +
//                        "mattis elit, in fringilla tellus. Etiam aliquam efficitur urna, id " +
//                        "ligula porta id. Curabitur libero ligula, pulvinar ac convallis nec, " +
//                        "hendrerit a lacus.", colour, images);
//    }
}