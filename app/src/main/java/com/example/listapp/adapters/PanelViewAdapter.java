package com.example.listapp.adapters;

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

import java.util.ArrayList;
import java.util.List;

//Todo : Fix setting the panelImage programatically from image path specified in Item model

public class PanelViewAdapter extends RecyclerView.Adapter<PanelViewAdapter.PanelViewHolder> {

//    Possible storage of image paths, item names and price. May be replaced with Item object itself.
    private List<Item> itemList = new ArrayList<>();
    private Context current_context;

    private OnItemClickListener mOnItemClickListener;


    public PanelViewAdapter(List<Item> itemList, Context context, OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        current_context = context;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PanelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.panel_view_item, parent, false); //item_square
        return new PanelViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PanelViewHolder holder, int position) {

        Item currentItem = itemList.get(position);
        Drawable material = current_context.getResources().getDrawable(R.drawable.handle_edge);
        //holder.panelImage.setImageResource(itemList.get(position).getFirstImage());
        holder.panelName.setText(combineNameArray(itemList.get(position).getName()));
        holder.panelPrice.setText("NZ$" + String.format("%.2f", currentItem.getPrice()));
        holder.id = itemList.get(position).getId();

        int imageId = current_context.getResources().getIdentifier(
                itemList.get(position).getFirstImage(), "drawable", current_context.getPackageName());
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

    private String combineNameArray(List<String> input) {
        String output = "";
        for (String s : input) {
            output = output + s + " ";
        }
        return output;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int id, View view);
    }

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


}
