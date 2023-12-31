package com.example.listapp.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.List;

public abstract class Handle implements Item {

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
    @Exclude
    List<String> categories;
    @Exclude
    String firestoreID;
    @PropertyName("lockable")
    Boolean lockable;
    @PropertyName("lockType")
    String lockType;


    /**
     * @return the Id of this Item instance
     */
    public int getId() {
        return id;
    }

    /**
     * @return the Firestore ID of the Item instance
     */
    public String getFirestoreID() {
        return firestoreID;
    }

    /**
     * @param fstoreID Firestore ID of the Item
     */
    public void setFirestoreID(String fstoreID) { firestoreID = fstoreID; }

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
        return lockType;
    }

    /**
     * Sets lockable status to true or false
     * @param lock String of lock type
     */
    public void setLockType(String lock) {
        lockType = lock;
    }

    /**
     * if a Handle object is lockable or not
     * @return
     */
    public boolean getLockable() {
        return lockable;
    }

    /**
     * Sets lockable status to true or false
     * @param lockStatus Boolean of lock status
     */
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
     * @return list of full qualified name of images
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

    /**
     * @param newDescription sets description
     */
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * @return list of dimensions in Long format
     */
    public List<Long> getDimensions() {
        return dimensions;
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

    /**
     * @return the weight of this handle
     */
    public  int getWeight() { return weight; }

    /**
     * @return the list of categories that the item is a member of
     */
    public List<String> getCategories(){
        List<String> category = new ArrayList<>();
        category.add("handle");
        return category;
    }
}
