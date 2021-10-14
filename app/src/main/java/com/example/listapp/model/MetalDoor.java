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
                     List<Integer> dimensions, List<String> name, String description,
                     List<String> colour, List<String> image) {
        super.id = id;
        super.weight = weight;
        super.viewCount = viewCount;
        super.price = price;
        super.dimensions = dimensions;
        super.name = name;
        super.description = description;
        super.colour = colour;
        super.image = image;
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
