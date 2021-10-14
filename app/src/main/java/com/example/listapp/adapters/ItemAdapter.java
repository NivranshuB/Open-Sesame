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
import androidx.recyclerview.widget.RecyclerView;

import com.example.listapp.R;
import com.example.listapp.model.DoorHandle;
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
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> { //ArrayAdapter,

    protected class ViewHolder extends RecyclerView.ViewHolder {
        //The common views of an item card go here
        ImageView panelImage;
        View panelBar;
        TextView panelName;
        TextView panelPrice;

        public ViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements common among all items assigned here
            panelImage = currentListViewItem.findViewById(R.id.panelImage);
            panelBar = currentListViewItem.findViewById(R.id.panelBar);
            panelName = currentListViewItem.findViewById(R.id.panelName);
            panelPrice = currentListViewItem.findViewById(R.id.panelPrice);
        }
    }

    private class DoorViewHolder extends ViewHolder {
        //The special views of a door card go here
        View materialEdgeTop;
        View materialEdgeBottom;
        TextView materialName;

        public DoorViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The special items of door items (wood door, metal door, glass door) assigned here
            materialEdgeTop = currentListViewItem.findViewById(R.id.materialEdgeTop);
            materialEdgeBottom = currentListViewItem.findViewById(R.id.materialEdgeBottom);
            materialName = currentListViewItem.findViewById(R.id.materialName);
        }
    }

    private class HandleViewHolder extends ViewHolder {
        //The special views of an doorknob card go here
        ImageView lockStatus, galleryImage1, galleryImage2;

        public HandleViewHolder(View currentListViewItem) {
            super(currentListViewItem);
            //The elements special to doorknobs assigned here
            lockStatus = currentListViewItem.findViewById(R.id.lockStatus);
            galleryImage1 = currentListViewItem.findViewById(R.id.galleryImage1);
            galleryImage2 = currentListViewItem.findViewById(R.id.galleryImage2);
        }
    }

    Context mContext;
    int layoutID;
    List<Item> items;

    public ItemAdapter(@NonNull Context context, int resource, List<Item> objects) {
//        super(context, resource, objects);
        mContext = context;
        layoutID = resource;
        items = objects;
    }

    private static int DOOR_TYPE = 1;
    private static int HANDLE_TYPE = 2;

    @Override
    public int getItemViewType(int position) {
        Item currentItem = items.get(position);
        if (currentItem.getClass() == WoodenDoor.class || currentItem.getClass() == GlassDoor.class
                || currentItem.getClass() == MetalDoor.class) {
            return DOOR_TYPE;
        } else if (currentItem.getClass() == DoorHandle.class) {
            return HANDLE_TYPE;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
//        if (layoutID == R.layout.item_square) {
//            return new DoorViewHolder(view);
//        } else if (layoutID == R.layout.door_handle_square) {
//            return new HandleViewHolder(view);
//        } else {
//            return new ViewHolder(view);
//        }
        if (viewType == DOOR_TYPE) {
            return new DoorViewHolder(view);
        } else if (viewType == HANDLE_TYPE) {
            return new HandleViewHolder(view);
        } else {
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the Number object for the current position
        Item currentItem = items.get(position);

        if (holder.getItemViewType() == DOOR_TYPE) {
            DoorViewHolder viewHolder = (DoorViewHolder) holder;
            populateDoorItem(currentItem, viewHolder);
        } else if (holder.getItemViewType() == HANDLE_TYPE) {
            HandleViewHolder viewHolder = (HandleViewHolder) holder;
            populateHandleItem(currentItem, viewHolder);
        }

        //Get the Number object for the current position
//        Item currentItem = items.get(position);
//        if (currentItem.getClass() == WoodenDoor.class || currentItem.getClass() == GlassDoor.class
//                || currentItem.getClass() == MetalDoor.class) {
//            populateDoorItem(currentItem, holder);
//        } else if (currentItem.getClass() == DoorHandle.class) {
//            populateHandleItem(currentItem, holder);
//        }

//        int imageIndex = mContext.getResources().getIdentifier(items.get(position).getImage().get(0), "drawable", mContext.getPackageName());
//
//        holder.panelName.setText(mergeStringList(items.get(position).getName()));
//        holder.panelPrice.setText("$" + items.get(position).getPrice());
//        holder.panelImage.setImageResource(imageIndex);
//        holder.panelBar
    }

    private String mergeStringList(List<String> stringList) {
        String mergedString = "";

        for (String s : stringList) {
            mergedString += s + " ";
        }

        return mergedString;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        //Get a reference to the current ListView item
//        View currentListViewItem = convertView;
//
//        // Check if the existing view is being reused, otherwise inflate the view
//        if (currentListViewItem == null) {
//            currentListViewItem = LayoutInflater.from(getContext()).inflate(layoutID, parent, false);
//        }
//
//        //Get the Number object for the current position
//        Item currentItem = items.get(position);
//        if (currentItem.getClass() == WoodenDoor.class || currentItem.getClass() == GlassDoor.class
//                || currentItem.getClass() == MetalDoor.class) {
//            return populateDoorItem(currentItem, currentListViewItem);
//        } else if (currentItem.getClass() == DoorHandle.class) {
//            return populateHandleItem(currentItem, currentListViewItem);
//        } else {
//            return null;
//        }
//
//    }

    /**
     * Special method that creates a unique view for a door item depending on what the category of
     * that door is (either wooden, metal or glass).
     *
     * @param currentItem
     * @param holder
     */
    private void populateDoorItem(Item currentItem, DoorViewHolder holder) {

        Drawable material = mContext.getResources().getDrawable(R.drawable.gold_gradient);
        String materialName = "";

        if (currentItem.getClass() == WoodenDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.wood_edge);
            materialName = stringListToString(currentItem.getName());
        } else if (currentItem.getClass() == MetalDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.metal_edge);
            materialName = stringListToString(currentItem.getName());
        } else if (currentItem.getClass() == GlassDoor.class) {
            material = mContext.getResources().getDrawable(R.drawable.glass_edge);
        }

        holder.materialEdgeTop.setBackground(material);
        holder.materialEdgeBottom.setBackground(material);
        holder.materialName.setText(materialName);

        //Set the attributed of list_view_number_item views
        int imageId = mContext.getResources().getIdentifier(
                currentItem.getFirstImage(), "drawable", mContext.getPackageName());

        holder.panelImage.setImageResource(imageId);

        holder.panelName.setText(stringListToString(currentItem.getName()));
        holder.panelPrice.setText(String.valueOf(currentItem.getPrice()));
    }

    /**
     * Special method that creates a unique view for an artistic door item
     *
     * @param currentItem
     * @param holder
     */
    private void populateHandleItem(Item currentItem, HandleViewHolder holder) {

        holder.panelName.setText(stringListToString(currentItem.getName()));

        //Set the attributed of list_view_number_item views
        int imageId = mContext.getResources().getIdentifier(
                currentItem.getFirstImage(), "drawable", mContext.getPackageName());

        int galleryImage1Id = mContext.getResources().getIdentifier(currentItem.getImage().get(1),
                "drawable", mContext.getPackageName());

        int galleryImage2Id = mContext.getResources().getIdentifier(currentItem.getImage().get(2),
                "drawable", mContext.getPackageName());

        holder.panelImage.setImageResource(imageId);
        holder.galleryImage1.setImageResource(galleryImage1Id);
        holder.galleryImage2.setImageResource(galleryImage2Id);

        int lockComponentId = mContext.getResources().getIdentifier("unlock",
                "drawable", mContext.getPackageName());

        if (currentItem.getLockable()) {
            lockComponentId = mContext.getResources().getIdentifier("lock", "drawable",
                    mContext.getPackageName());
        }

        holder.lockStatus.setImageResource(lockComponentId);

        holder.panelPrice.setText(String.valueOf(currentItem.getPrice()));
    }

    private String stringListToString(List<String> stringList) {
        String name = "";
        for (String s : stringList) {
            name = name + s;
        }
        return name.trim();
    }

//    /**
//     * Special method that creates a unique view for a door item depending on what the category of
//     * that door is (either wooden, metal or glass).
//     *
//     * @param currentItem
//     * @param currentListViewItem
//     * @return
//     */
//    private View populateDoorItem(Item currentItem, View currentListViewItem) {
//
//        DoorViewHolder doorViewHolder = new DoorViewHolder(currentListViewItem);
//
//        Drawable material = mContext.getResources().getDrawable(R.drawable.gold_gradient);
//        String materialName = "";
//
//        if (currentItem.getClass() == WoodenDoor.class) {
//            material = mContext.getResources().getDrawable(R.drawable.wood_edge);
//            materialName = stringListToString(currentItem.getName());
//        } else if (currentItem.getClass() == MetalDoor.class) {
//            material = mContext.getResources().getDrawable(R.drawable.metal_edge);
//            materialName = stringListToString(currentItem.getName());
//        } else if (currentItem.getClass() == GlassDoor.class) {
//            material = mContext.getResources().getDrawable(R.drawable.glass_edge);
//        } else {
//            return null;
//        }
//
//        doorViewHolder.materialEdgeTop.setBackground(material);
//        doorViewHolder.materialEdgeBottom.setBackground(material);
//        doorViewHolder.materialName.setText(materialName);
//
//        //Set the attributed of list_view_number_item views
//        int imageId = mContext.getResources().getIdentifier(
//                currentItem.getFirstImage(), "drawable", mContext.getPackageName());
//
//        doorViewHolder.panelImage.setImageResource(imageId);
//
//        doorViewHolder.panelName.setText(stringListToString(currentItem.getName()));
//        doorViewHolder.panelPrice.setText(String.valueOf(currentItem.getPrice()));
//
//        return currentListViewItem;
//    }


//    /**
//     * Special method that creates a unique view for an artistic door item
//     *
//     * @param currentItem
//     * @param currentListViewItem
//     * @return
//     */
//    private View populateHandleItem(Item currentItem, View currentListViewItem) {
//
//        HandleViewHolder handleViewHolder = new HandleViewHolder(currentListViewItem);
//
//        handleViewHolder.panelName.setText(stringListToString(currentItem.getName()));
//
//        //Set the attributed of list_view_number_item views
//        int imageId = mContext.getResources().getIdentifier(
//                currentItem.getFirstImage(), "drawable", mContext.getPackageName());
//
//        int galleryImage1Id = mContext.getResources().getIdentifier(currentItem.getImage().get(1),
//                "drawable", mContext.getPackageName());
//
//        int galleryImage2Id = mContext.getResources().getIdentifier(currentItem.getImage().get(2),
//                "drawable", mContext.getPackageName());
//
//        handleViewHolder.panelImage.setImageResource(imageId);
//        handleViewHolder.galleryImage1.setImageResource(galleryImage1Id);
//        handleViewHolder.galleryImage2.setImageResource(galleryImage2Id);
//
//        int lockComponentId = mContext.getResources().getIdentifier("unlock",
//                "drawable", mContext.getPackageName());
//
//        if (currentItem.getLockable()) {
//            lockComponentId = mContext.getResources().getIdentifier("lock", "drawable",
//                    mContext.getPackageName());
//        }
//
//        handleViewHolder.lockStatus.setImageResource(lockComponentId);
//
//        handleViewHolder.panelPrice.setText(String.valueOf(currentItem.getPrice()));
//
//        return currentListViewItem;
//    }

//    private String stringListToString(List<String> stringList) {
//        String name = "";
//        for (String s : stringList) {
//            name = name + s;
//        }
//        return name.trim();
//    }

}
