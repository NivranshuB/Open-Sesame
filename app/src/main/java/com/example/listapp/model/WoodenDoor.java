package com.example.listapp.model;

import com.google.firebase.firestore.PropertyName;

import java.util.List;

/**
 * Open Sesame
 *
 * -Responsibilities of a wooden door...
 */
public class WoodenDoor extends Door {

    String materialType;

    public WoodenDoor()
    {
        //Empty constructor required
    }

    public WoodenDoor(int id, int weight, int viewCount, float price,
                     List<Long> dimensions, List<String> name, String description,
                     List<String> colour, List<String> image) {
        this.id = id;
        this.weight = weight;
        this.viewCount = viewCount;
        this.price = price;
        this.dimensions = dimensions;
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.image = image;
    }
    /**
     * @return the full qualified name of the material image of this Item instance
     */
    @Override
    @PropertyName("type")
    public String getMaterialType() {
        return materialType;
    }

    @PropertyName("type")
    public void setMaterialType(String type) {
        materialType = type;
    }
}
