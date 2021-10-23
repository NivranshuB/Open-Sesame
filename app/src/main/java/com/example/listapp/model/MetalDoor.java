package com.example.listapp.model;

import java.util.List;

/**
 * The MetalDoor class is an overall Item class that is more specifically a subtype of Door class.
 * Contrary to the GlassDoor type this class does have the material type attribute.
 */
public class MetalDoor extends Door {

    String materialType;

    /**
     * Implicit constructor for a MetalDoor instance
     */
    public MetalDoor()
    {
        //Empty constructor required
    }

    /**
     * Explicit constructor for a MetalDoor instance
     * @param id
     * @param weight
     * @param viewCount
     * @param price
     * @param dimensions
     * @param name
     * @param description
     * @param colour
     * @param image
     */
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

    /**
     * Set the material type of a Metal door
     * @param newMaterialType
     */
    public void setMaterialType(String newMaterialType) {
        materialType = newMaterialType;
    }
}
