package com.example.listapp.model;

import java.util.List;

/**
 * Open Sesame
 *
 * -Responsibilities of a glass door...
 */
public class GlassDoor extends Door {

    String materialImage;

    public GlassDoor()
    {
        //Empty constructor required
    }

    public GlassDoor(int id, int weight, int viewCount, float price,
                     List<Integer> dimensions, List<String> name, String description,
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
    public String getMaterialImage() {
        return null;
    }

    public void setMaterialImage(String newMaterialImage) {
        materialImage = newMaterialImage;
    }
}
