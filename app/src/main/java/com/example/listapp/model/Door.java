package com.example.listapp.model;

import java.util.List;

/**
 * Open Sesame
 *
 * This class represents a standard door item instance that implements the Item interface. This
 * class is abstract and only contains the fields and methods that all the different categories of
 * doors will have.
 */
public abstract class Door implements Item {

    int id, weight, viewCount;
    float price;
    List<Integer> dimensions;
    String description;
    List<String> colour, image, name;

    /**
     * @return the Id of this Item instance
     */
    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    /**
     * @return the price of this Item instance
     */
    public float getPrice(){ return price;}

    /**
     * @return the name of this Item instance
     */
    public List<String> getName() {
        return name;
    }

    public void setName(List<String> newName) {
        name = newName;
    }

    /**
     * @return the full qualified name of the first image of this Item instance
     */
    public String getFirstImage() {
        return image.get(0);
    }

    /**
     * @return the list of all full qualified names of the images of this item instance (ignore the
     * material image)
     */
    public List<String> getImage() {
        return image;
    }

    public void setImages(List<String> newImageUrls) {
        image = newImageUrls;
    }

    /**
     * @return the string description of this Item
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * @return the view count of this Item instance
     */
    public int getViewCount() {
        return viewCount;
    }


    /**
     * Increase the view count of this image by 1
     */
    public void incrementViewCount() {
        viewCount++;
    }

    /**
     * Reset the view count of this image to 0
     */
    public void resetViewCount() {
        viewCount = 0;
    }
}
