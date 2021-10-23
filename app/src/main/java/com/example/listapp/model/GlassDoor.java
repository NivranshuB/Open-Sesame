package com.example.listapp.model;

import java.util.List;

/**
 * This class is an overall Item class that is more specifically a subtype of Door class. Contrary
 * other Door types a GlassDoor does not have the material type attribute.
 */
public class GlassDoor extends Door {
    /**
     * Implicit constructor for a GlassDoor instance
     */
    public GlassDoor()
    {
        //Empty constructor required
    }

    /**
     * Explicit constructor for a GlassDoor instance
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
    public GlassDoor(int id, int weight, int viewCount, float price,
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
}
