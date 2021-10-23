package com.example.listapp.adapters;

import static com.example.listapp.data.TextFormatting.formatPrice;
import static com.example.listapp.data.TextFormatting.mergeStringList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listapp.R;
import com.example.listapp.model.DoorHandle;
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Item;
import com.example.listapp.model.MetalDoor;
import com.example.listapp.model.WoodenDoor;

import java.util.List;

/**
 * This class is responsible to adapt an Item of our application to a recycler view UI component.
 * This adapter is used in ListActivity page, to showcase the different category list item, the
 * Search Activity page and the Favourites page.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> { //ArrayAdapter,

    private static int DOOR_TYPE = 1;
    private static int HANDLE_TYPE = 2;

    Context mContext;
    int layoutID;
    List<Item> items;
    private OnItemClickListener mOnItemClickListener;

    /**
     * An interface which needs to be implemented by any activity class that wants to provide an
     * on click functionality when an item is clicked.
     */
    public interface OnItemClickListener {
        void onItemClick(int id, View view);
    }

    /**
     * Inner view holder class which holds all the common views of an item card.
     */
    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //The common views of an item card go here
        ImageView panelImage;
        View panelBar;
        TextView panelName;
        TextView panelPrice;
        OnItemClickListener onItemClickListener;

        int id;

        public ViewHolder(View currentListViewItem, OnItemClickListener listener) {
            super(currentListViewItem);
            //The elements common among all items assigned here
            panelImage = currentListViewItem.findViewById(R.id.panelImage);
            panelBar = currentListViewItem.findViewById(R.id.panelBar);
            panelName = currentListViewItem.findViewById(R.id.panelName);
            panelPrice = currentListViewItem.findViewById(R.id.panelPrice);

            this.onItemClickListener = listener;

            currentListViewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(id, panelImage);
        }
    }

    /**
     * This inner door view holder class only holds the views in an item card that are exclusive to
     * Door type objects.
     */
    private class DoorViewHolder extends ViewHolder {
        //The special views of a door card go here
        View materialEdgeTop;

        public DoorViewHolder(View currentListViewItem, OnItemClickListener oicl) {
            super(currentListViewItem, oicl);
            //The special items of door items (wood door, metal door, glass door) assigned here
            materialEdgeTop = currentListViewItem.findViewById(R.id.materialEdgeTop);
        }
    }

    /**
     * This inner handle view holder class only holds the views in an item card that are exclusive
     * to Handle type objects.
     */
    private class HandleViewHolder extends ViewHolder {
        //The special views of a door handle card go here
        ImageView lockStatus, galleryImage1, galleryImage2;

        public HandleViewHolder(View currentListViewItem, OnItemClickListener oicl) {
            super(currentListViewItem, oicl);
            //The elements special to door handles assigned here
            lockStatus = currentListViewItem.findViewById(R.id.lockStatus);
            galleryImage1 = currentListViewItem.findViewById(R.id.galleryImage1);
            galleryImage2 = currentListViewItem.findViewById(R.id.galleryImage2);
        }
    }

    /**
     * Explicit constructor that constructs an initialises an ItemAdapter instance.
     * @param context
     * @param resource
     * @param objects
     * @param onItemClickListener
     */
    public ItemAdapter(@NonNull Context context, int resource, List<Item> objects,
                       OnItemClickListener onItemClickListener) {
        mContext = context;
        layoutID = resource;
        items = objects;
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * Return the item view type at a given position in the list. The item view type can either be
     * Door type or Handle type.
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Item currentItem = items.get(position);
        if (currentItem.getClass() == WoodenDoor.class || currentItem.getClass() == GlassDoor.class
                || currentItem.getClass() == MetalDoor.class) {
            return DOOR_TYPE;
        } else if (currentItem.getClass() == DoorHandle.class) {
            return HANDLE_TYPE;
        }
        return 0;
    }

    /**
     * Instantiates a viewHolder instance depending on the type of item that it is creating the item
     * view for.
     * @param parent
     * @param viewType
     * @return viewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
        if (viewType == DOOR_TYPE) {
            return new DoorViewHolder(view, mOnItemClickListener);
        } else if (viewType == HANDLE_TYPE) {
            return new HandleViewHolder(view, mOnItemClickListener);
        } else {
            return new ViewHolder(view, mOnItemClickListener);
        }
    }

    /**
     * Given the position of the item on the list and a view holder that contains all the views
     * required to construct that item's UI card, populates this UI component dynamically.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the Number object for the current position
        Item currentItem = items.get(position);
        holder.id = currentItem.getId();

        if (holder.getItemViewType() == DOOR_TYPE) {
            DoorViewHolder viewHolder = (DoorViewHolder) holder;
            populateDoorItem(currentItem, viewHolder);
        } else if (holder.getItemViewType() == HANDLE_TYPE) {
            HandleViewHolder viewHolder = (HandleViewHolder) holder;
            populateHandleItem(currentItem, viewHolder);
        }
    }

    /**
     * @return The number of items held in this adapter
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Special method that creates a unique view for a door item depending on what the category of
     * that door is (either wooden, metal or glass).
     *
     * @param currentItem
     * @param holder
     */
    private void populateDoorItem(Item currentItem, DoorViewHolder holder) {

        //Top edge colour of an Item card
        Drawable material = mContext.getResources().getDrawable(R.drawable.handle_edge);

        if (currentItem.getClass() == WoodenDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.wood_edge);
        } else if (currentItem.getClass() == MetalDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.metal_edge);
        } else if (currentItem.getClass() == GlassDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.glass_edge);
        }

        if (holder.materialEdgeTop != null) {
            holder.materialEdgeTop.setBackground(material);
        }

        //Set the panel image, name and price of the card
        int imageId = mContext.getResources().getIdentifier(
                currentItem.getFirstImage(), "drawable", mContext.getPackageName());

        holder.panelImage.setImageResource(imageId);

        holder.panelName.setText(mergeStringList(currentItem.getName()));
        holder.panelPrice.setText(formatPrice(currentItem.getPrice()));
    }

    /**
     * Special method that creates a unique view for a handle item depending on what it's handle
     * specific attributes are.
     * @param currentItem
     * @param holder
     */
    private void populateHandleItem(Item currentItem, HandleViewHolder holder) {

        holder.panelName.setText(mergeStringList(currentItem.getName()));

        //Set all three images of a handle type card
        int imageId = mContext.getResources().getIdentifier(
                currentItem.getFirstImage(), "drawable", mContext.getPackageName());

        int galleryImage1Id = mContext.getResources().getIdentifier(currentItem.getImage().get(1),
                "drawable", mContext.getPackageName());

        int galleryImage2Id = mContext.getResources().getIdentifier(currentItem.getImage().get(2),
                "drawable", mContext.getPackageName());

        holder.panelImage.setImageResource(imageId);

        if (holder.galleryImage1 != null) {
            holder.galleryImage1.setImageResource(galleryImage1Id);
        }

        if (holder.galleryImage2 != null) {
            holder.galleryImage2.setImageResource(galleryImage2Id);
        }

        //Set the lock status component of the handle UI card
        if (holder.lockStatus != null) {
            if (!currentItem.getLockable()) {
                holder.lockStatus.setVisibility(View.INVISIBLE);
            } else {
                holder.lockStatus.setVisibility(View.VISIBLE);
            }
        }

        //Set the panel price of a UI card
        holder.panelPrice.setText(formatPrice(currentItem.getPrice()));
    }
}
