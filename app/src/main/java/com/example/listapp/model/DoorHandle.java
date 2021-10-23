package com.example.listapp.model;

import java.util.List;

/**
 * This class is an overall Item class that is more specifically a subtype of Handle class. Contrary
 * to Door items a DoorHandle items also contains the lockable and lock status attributes while it
 * does not contain the dimensions attribute or the material type attribute.
 */
public class DoorHandle extends Handle {

    public DoorHandle() {

    }

    /**
     * Explicit constructor for a DoorHandle instance
     * @param id
     * @param weight
     * @param viewCount
     * @param price
     * @param dimensions
     * @param name
     * @param description
     * @param colour
     * @param image
     * @param lockable
     */
    public DoorHandle(int id, int weight, int viewCount, float price,
                     List<Long> dimensions, List<String> name, String description,
                     List<String> colour, List<String> image, Boolean lockable) {
        this.id = id;
        this.weight = weight;
        this.viewCount = viewCount;
        this.price = price;
        this.dimensions = dimensions;
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.image = image;
        this.lockable = lockable;
    }
}
