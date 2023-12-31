package com.example.listapp.model;

import android.util.Log;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.util.List;

/**
 * Open Sesame
 *
 * This class represents a standard door item instance that implements the Item interface. This
 * class is abstract and only contains the fields and methods that all the different categories of
 * doors will have.
 */
public abstract class Door implements Item {

    @PropertyName("id")
    int id;
    @PropertyName("weight")
    int weight;
    @PropertyName("viewCount")
    int viewCount;
    @PropertyName("price")
    float price;
    @PropertyName("dimensions")
    List<Long> dimensions;
    @PropertyName("description")
    String description;
    @PropertyName("colour")
    List<String> colour;
    @PropertyName("image")
    List<String> image;
    @PropertyName("name")
    List<String> name;
    @PropertyName("categories")
    List<String> categories;
    @Exclude
    String firestoreID;

    /**
     * @return the Id of this Item instance
     */
    public int getId() {
        return id;
    }

    /**
     * @return the Firestore ID of this Item instance
     */
    public String getFirestoreID(){
        return firestoreID;
    }

    /**
     * @param fstoreID Firestore ID of this Item
     */
    public void setFirestoreID(String fstoreID) {firestoreID = fstoreID; }

    /**
     * @return the price of this Item instance.
     */
    public float getPrice(){ return price;}

    /**
     * If a door has a material type (wooden doors and metal doors) then this method will be
     * overriden by the sub class and will return the materialType field value. Else this method
     * returns an empty string.
     */
    public String getMaterialType() {
        return "";
    }

    /**
     * A door instance cannot have a lock type only a handle instance can so return an empty string
     * for lockType.
     */
    public String getLockType() {
        return "";
    }

    /**
     * Does nothing as Door cannot have a lock type.
     * @param lock
     */
    public void setLockType(String lock) {}

    /**
     * A door instance cannot be lockable only a handle instance can therefore return false.
     */
    public boolean getLockable() {
        return false;
    }

    /**
     * Does nothing as Door cannot be lockable
     * @param lockStatus
     */
    public void setLockable(boolean lockStatus) {}

    /**
     * @return the name of this Item instance
     */
    public List<String> getName() {
        return name;
    }

    /**
     * @return list of categories the Item instance belongs to.
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @return weight of Item instance.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return list of dimensions (length, height, width) in long format.
     */
    public List<Long> getDimensions() {
        return dimensions;
    }

    /**
     * @return list of colours the Item is available in.
     */
    public List<String> getColour() {
        return colour;
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
