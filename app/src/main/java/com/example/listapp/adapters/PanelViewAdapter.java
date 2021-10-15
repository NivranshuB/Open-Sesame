package com.example.listapp.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listapp.R;
import com.example.listapp.model.Item;

import java.util.ArrayList;
import java.util.List;

//Todo : Fix setting the panelImage programatically from image path specified in Item model

public class PanelViewAdapter extends RecyclerView.Adapter<PanelViewAdapter.PanelViewHolder> {

//    Possible storage of image paths, item names and price. May be replaced with Item object itself.
//    private ArrayList<String> itemImagePaths = new ArrayList<>();
//    private ArrayList<String> itemNames = new ArrayList<>();
//    private ArrayList<String> itemPrices = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private Context current_context;


    public PanelViewAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        current_context = context;
    }

    @NonNull
    @Override
    public PanelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.panel_view_item, parent, false); //item_square
        return new PanelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanelViewHolder holder, int position) {
        //holder.panelImage.setImageResource(itemList.get(position).getFirstImage());
        holder.panelName.setText(combineNameArray(itemList.get(position).getName()));
        holder.panelPrice.setText("$" + Float.toString(itemList.get(position).getPrice()));

        int imageId = current_context.getResources().getIdentifier(
                itemList.get(position).getFirstImage(), "drawable", current_context.getPackageName());
        holder.panelImage.setImageResource(imageId);
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

    public class PanelViewHolder extends RecyclerView.ViewHolder {
        ImageView panelImage;
        TextView panelName;
        TextView panelPrice;

        public PanelViewHolder(@NonNull View itemView) {
            super(itemView);
            panelImage = itemView.findViewById(R.id.panelImage);
            panelName = itemView.findViewById(R.id.panelName);
            panelPrice = itemView.findViewById(R.id.panelPrice);
        }
    }


}
