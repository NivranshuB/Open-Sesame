package com.example.listapp.model;

import java.util.List;

public abstract class Handle implements Item {

    int id, height, width, length, weight, price, viewCount;
    List<String> name;
    List<String> colors;
    List<String> images;
    String description;
    Boolean lockable;
    String lockType;

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
     * @return the price of the item
     */
    public float getPrice() {
        return price;
    }

    /**
     * If a door has a material type (wooden doors and metal doors) then this method will be
     * overriden by the sub class and will return the materialType field value. Else this method
     * returns an empty string.
     */
    public String getMaterialType() {
        return "";
    }

    /**
     * For a Handle object return the lock type of the object.
     */
    public String getLockType() {
        return "";
    }

    public void setLockType(String lock) {
        lockType = lock;
    }

    public boolean getLockable() {
        return lockable;
    }

    public void setLockable(boolean lockStatus) {
        lockable = lockStatus;
    }

    /**
     * @return the name of this Item instance
     */
    public List<String> getName() {
        return name;
    }

    /**
     * @return the full qualified name of the first image of this Item instance
     */
    public String getFirstImage() {
        return images.get(0);
    }

    /**
     * @return the list of all full qualified names of the images of this item instance (ignore the
     * material image)
     */
    public List<String> getImage() {
        return images;
    }

    public void setImages(List<String> newImageUrls) {
        images = newImageUrls;
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
