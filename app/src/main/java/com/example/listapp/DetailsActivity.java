package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.listapp.model.Door;
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

        public ViewHolder() {
            //The elements common among all items assigned here
            itemName = findViewById(R.id.item_name);
            description = findViewById(R.id.item_description_text);
            itemSpecification = findViewById(R.id.item_specification_text);
            price = findViewById(R.id.item_price);
        }
    }

    Item defaultItem;
    Item itemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Log.d("detailsActivity", "Details activity launched");
        System.out.println("Details activity launched");

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_details);
        setSupportActionBar(toolbar);

        ViewHolder detailsActivityVh = new ViewHolder();

        createDefaultItem();

        String nameString = "";

        for (String s : defaultItem.getName()) {
            nameString += " " + s;
        }

        detailsActivityVh.itemName.setText(nameString);
        detailsActivityVh.description.setText(defaultItem.getDescription());
        detailsActivityVh.price.setText("$" + defaultItem.getPrice());

    }

    private void createDefaultItem() {
        List<Integer> dimensions = new ArrayList<>();
        dimensions.add(Integer.valueOf(2355));
        dimensions.add(Integer.valueOf(566));
        dimensions.add(Integer.valueOf(36));

        List<String> name = new ArrayList<>();
        name.add("Authentic");
        name.add("Gloss");
        name.add("Finished");
        name.add("Timber");
        name.add("Door");

        List<String> colour = new ArrayList<>();
        colour.add("#000000");
        colour.add("#000000");
        colour.add("#000000");

        List<String> images = new ArrayList<>();
        images.add("handle3_1");
        images.add("handle3_2");
        images.add("handle3_3");

        defaultItem = new WoodenDoor(1, 560, 43, 50.50f, dimensions, name,
                "dsafk sadflkd dslfka dsa", colour, images);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.description_relative_layout);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        relativeLayout.startAnimation(animation);

        ViewCompat.setTransitionName(findViewById(R.id.details_image_view), "topPicksImageTransition");

    }
}