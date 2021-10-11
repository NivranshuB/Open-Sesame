package com.example.listapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        public ViewHolder(View currentListViewItem) {
            //The elements common among all door items
        }
    }

    private class WoodenDoorViewHolder extends ViewHolder {
        //The special views of a wooden door card go here

        public WoodenDoorViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements special to wooden doors assigned here
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

        public MetalDoorViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements special to metal doors assigned here
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
