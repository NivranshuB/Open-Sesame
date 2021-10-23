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
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Item;
import com.example.listapp.model.MetalDoor;
import com.example.listapp.model.WoodenDoor;

import java.util.List;

/**
 * This class is responsible to adapt an Item of our application to a recycler view UI component.
 * This adapter is used to create the most viewed panel in the Main Activity page. The difference
 * between this class and the ItemAdapter class is that this class creates a simpler 'preview' item
 * card where the design across different categories is much more consistent.
 */
public class PanelViewAdapter extends RecyclerView.Adapter<PanelViewAdapter.PanelViewHolder> {

    private List<Item> itemList;
    private Context current_context;

    private OnItemClickListener mOnItemClickListener;

    /**
     * Inner view holder class which holds all the views of an panel view item card.
     */
    public class PanelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView panelImage;
        TextView panelName;
        TextView panelPrice;
        View materialEdgeTop;
        OnItemClickListener onItemClickListener;

        int id;

        public PanelViewHolder(@NonNull View itemView , OnItemClickListener listener) {
            super(itemView);
            panelImage = itemView.findViewById(R.id.panelImage);
            panelName = itemView.findViewById(R.id.panelName);
            panelPrice = itemView.findViewById(R.id.panelPrice);
            materialEdgeTop = itemView.findViewById(R.id.materialEdgeTop);

            this.onItemClickListener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(id, panelImage);
        }
    }

    /**
     * Explicit constructor that constructs an initialises a PanelViewAdapter instance.
     * @param itemList
     * @param context
     * @param onItemClickListener
     */
    public PanelViewAdapter(List<Item> itemList, Context context,
                            OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        current_context = context;
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * Instantiates a generic viewHolder instance that can be applied to both Door and Handle type
     * items.
     * @param parent
     * @param viewType
     * @return viewHolder
     */
    @NonNull
    @Override
    public PanelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.panel_view_square,
                parent, false); //item_square
        return new PanelViewHolder(view, mOnItemClickListener);
    }

    /**
     * Given the position of the item on the list and a view holder that contains all the views
     * required to construct that item's UI card, populates this UI component dynamically.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PanelViewHolder holder, int position) {

        //Set the panel name, price and image of this item card
        Item currentItem = itemList.get(position);
        Drawable material = current_context.getResources().getDrawable(R.drawable.handle_edge);
        holder.panelName.setText(mergeStringList(itemList.get(position).getName()));
        holder.panelPrice.setText(formatPrice(currentItem.getPrice()));
        holder.id = itemList.get(position).getId();

        int imageId = current_context.getResources().getIdentifier(
                itemList.get(position).getFirstImage(), "drawable",
                current_context.getPackageName());
        holder.panelImage.setImageResource(imageId);

        if (currentItem.getClass() == WoodenDoor.class) {
            material = current_context.getResources().getDrawable(R.drawable.wood_edge);
        } else if (currentItem.getClass() == MetalDoor.class) {
            material = current_context.getResources().getDrawable(R.drawable.metal_edge);
        } else if (currentItem.getClass() == GlassDoor.class) {
            material = current_context.getResources().getDrawable(R.drawable.glass_edge);
        }

        if (holder.materialEdgeTop != null) {
            holder.materialEdgeTop.setBackground(material);
        }
    }

    /**
     * @return The number of items in this adapter
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * Calls the onItemClick method when an item card is clicked.
     */
    public interface OnItemClickListener {
        void onItemClick(int id, View view);
    }
}
