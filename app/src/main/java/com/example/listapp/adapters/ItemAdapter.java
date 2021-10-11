package com.example.listapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listapp.R;
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Item;
import com.example.listapp.model.MetalDoor;
import com.example.listapp.model.WoodenDoor;

import java.util.List;

/**
 * This adapter class adapts and decides the user interface layout of an Item instance in Recycler
 * or ListView. The card view representing the item instance will be different depending on what
 * category that item belongs to.
 */
public class ItemAdapter extends ArrayAdapter {

    private class ViewHolder {
        //The common views of an item card go here

        public ViewHolder(View currentListViewItem) {
            //The elements common among all items assigned here

        }
    }

        private class DoorViewHolder extends ViewHolder {
            //The special views of a door card go here
            View materialEdgeTop;
            View materialEdgeBottom;
            TextView materialName;
            ImageView panelImage;
            View panelBar;
            TextView panelName;
            TextView panelPrice;

            public DoorViewHolder(View currentListViewItem) {
                super(currentListViewItem);
                //The special items of door items (wood door, metal door, glass door) assigned here
                materialEdgeTop = currentListViewItem.findViewById(R.id.materialEdgeTop);
                materialEdgeBottom = currentListViewItem.findViewById(R.id.materialEdgeBottom);
                materialName = currentListViewItem.findViewById(R.id.materialName);
                panelImage = currentListViewItem.findViewById(R.id.panelImage);
                panelBar = currentListViewItem.findViewById(R.id.panelBar);
                panelName = currentListViewItem.findViewById(R.id.panelName);
                panelPrice = currentListViewItem.findViewById(R.id.panelPrice);
            }
        }

        private class DoorknobViewHolder extends ViewHolder {
        //The special views of an doorknob card go here

            public DoorknobViewHolder(View currentListViewItem) {
                super(currentListViewItem);
                //The elements special to doorknobs assigned here
        }
    }

    int layoutID;
    List<Item> items;
    Context mContext;

    public ItemAdapter(@NonNull Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        layoutID = resource;
        mContext = context;
        items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get a reference to the current ListView item
        View currentListViewItem = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(layoutID, parent, false);
        }

        //Get the Number object for the current position
        Item currentItem = items.get(position);
        if (currentItem.getClass() == WoodenDoor.class || currentItem.getClass() == GlassDoor.class
        || currentItem.getClass() == MetalDoor.class) {
            return populateDoorItem(currentItem, currentListViewItem);
        } else if (currentItem.getClass() == GlassDoor.class) {
            return populateDoorknobItem(currentItem, currentListViewItem);
        } else {
            return null;
        }

    }

    /**
     * Special method that creates a unique view for a door item depending on what the category of
     * that door is (either wooden, metal or glass).
     * @param currentItem
     * @param currentListViewItem
     * @return
     */
    private View populateDoorItem(Item currentItem, View currentListViewItem) {

        DoorViewHolder doorViewHolder = new DoorViewHolder(currentListViewItem);

        Drawable material = mContext.getResources().getDrawable(R.drawable.gold_gradient);
        String materialName = "";

        if (currentItem.getClass() == WoodenDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.wood_edge);
            materialName = currentItem.getName();
        } else if (currentItem.getClass() ==  MetalDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.metal_edge);
            materialName = currentItem.getName();
        } else if (currentItem.getClass() == GlassDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.glass_edge);
        } else {
            return null;
        }

        doorViewHolder.materialEdgeTop.setBackground(material);
        doorViewHolder.materialEdgeBottom.setBackground(material);
        doorViewHolder.materialName.setText(materialName);

        //Set the attributed of list_view_number_item views
        int imageId = mContext.getResources().getIdentifier(
                currentItem.getFirstImage(), "drawable", mContext.getPackageName());

        doorViewHolder.panelImage.setImageResource(imageId);

        doorViewHolder.panelName.setText(currentItem.getName());
        doorViewHolder.panelPrice.setText(String.valueOf(currentItem.getPrice()));

        return currentListViewItem;
    }


    /**
     * Special method that creates a unique view for an artistic door item
     * @param currentItem
     * @param currentListViewItem
     * @return
     */
    private View populateDoorknobItem(Item currentItem, View currentListViewItem) {
        //todo
        return null;
    }


}
