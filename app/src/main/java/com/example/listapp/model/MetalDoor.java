package com.example.listapp.model;

import java.util.List;

/**
 * Open Sesame
 *
 * -Responsibilities of a metal door...
 */
public class MetalDoor extends Door {

    String materialType;

    public MetalDoor()
    {
        //Empty constructor required
    }

    public MetalDoor(int id, int weight, int viewCount, float price,
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
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String newMaterialType) {
        materialType = newMaterialType;
    }
}
