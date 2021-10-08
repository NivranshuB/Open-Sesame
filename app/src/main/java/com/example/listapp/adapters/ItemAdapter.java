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
import com.example.listapp.model.ArtisticDoor;
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
        View materialEdgeTop;
        View materialEdgeBottom;
        TextView materialName;
        ImageView panelImage;
        View panelBar;
        TextView panelName;
        TextView panelPrice;

        public ViewHolder(View currentListViewItem) {
            //The elements common among all door items
            materialEdgeTop = currentListViewItem.findViewById(R.id.materialEdgeTop);
            materialEdgeBottom = currentListViewItem.findViewById(R.id.materialEdgeBottom);
            materialName = currentListViewItem.findViewById(R.id.materialName);
            panelImage = currentListViewItem.findViewById(R.id.panelImage);
            panelBar = currentListViewItem.findViewById(R.id.panelBar);
            panelName = currentListViewItem.findViewById(R.id.panelName);
            panelPrice = currentListViewItem.findViewById(R.id.panelPrice);
        }
    }

    private class WoodenDoorViewHolder extends ViewHolder {
        //The special views of a wooden door card go here
        TextView materialName;

        public WoodenDoorViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements special to wooden doors assigned here
            materialName = currentListViewItem.findViewById(R.id.materialName);
        }
    }

    private class GlassDoorViewHolder extends ViewHolder {
        //The special views of a glass door card go here

        public GlassDoorViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements special to glass doors assigned here
        }
    }

    private class MetalDoorViewHolder extends ViewHolder {
        //The special views of a metal door card go here
        TextView materialName;

        public MetalDoorViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements special to metal doors assigned here
            materialName = currentListViewItem.findViewById(R.id.materialName);
        }
    }

    private class ArtisticDoorViewHolder extends ViewHolder {
        //The special views of an artistic door card go here

        public ArtisticDoorViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements special to artistic doors assigned here
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
        if (currentItem.getClass() == WoodenDoor.class)
            // Populate the Current ListView Item with Numbers data and layout
            return populateWoodenDoorItem(currentItem, currentListViewItem);

        else if (currentItem.getClass() == GlassDoor.class)
            // Populate the Current ListView Item with Colors data and layout
            return populateGlassDoorItem(currentItem, currentListViewItem);

        else if (currentItem.getClass() == MetalDoor.class)
            // Populate the Current ListView Item with Colors data and layout
            return populateMetalDoorItem(currentItem, currentListViewItem);

        else if (currentItem.getClass() == ArtisticDoor.class)
            // Populate the Current ListView Item with Colors data and layout
            return populateArtisticDoorItem(currentItem, currentListViewItem);

        else return null;

    }

    /**
     * Special method that creates a unique view for a door item
     * @param currentItem
     * @param currentListViewItem
     * @return
     */
    private View populateDoorItem(Item currentItem, View currentListViewItem) {

        ViewHolder doorViewHolder = new ViewHolder(currentListViewItem);

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
     * Special method that creates a unique view for a wooden door item
     * @param currentItem
     * @param currentListViewItem
     * @return
     */
    private View populateWoodenDoorItem(Item currentItem, View currentListViewItem) {
        //todo
        return null;
    }

    /**
     * Special method that creates a unique view for a glass door item
     * @param currentItem
     * @param currentListViewItem
     * @return
     */
    private View populateGlassDoorItem(Item currentItem, View currentListViewItem) {
        //todo
        return null;
    }

    /**
     * Special method that creates a unique view for a metal door item
     * @param currentItem
     * @param currentListViewItem
     * @return
     */
    private View populateMetalDoorItem(Item currentItem, View currentListViewItem) {
        //todo
        return null;
    }

    /**
     * Special method that creates a unique view for an artistic door item
     * @param currentItem
     * @param currentListViewItem
     * @return
     */
    private View populateArtisticDoorItem(Item currentItem, View currentListViewItem) {
        //todo
        return null;
    }


}
