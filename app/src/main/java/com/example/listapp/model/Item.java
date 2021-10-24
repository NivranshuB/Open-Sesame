package com.example.listapp.model;

import java.util.List;

/**
 * Open Sesame
 *
 * This interface outlines all the responsibilities of all the classes that want to be considered
 * as 'items' in the application. This includes all the different types of doors. Implementing all
 * the methods in the specified manner ensures that a particular item can be represented in the
 * user interface.
 */
public interface Item {

    /**
     * @return the Id of this Item instance
     */
    int getId();

    /**
     * @return the Firestore ID of the Item instance
     */
    String getFirestoreID();

    /**
     * @param fstoreID Firestore ID of the Item
     */
    void setFirestoreID(String fstoreID);

    /**
     * @return the name of this Item instance
     */
    List<String> getName();

    /**
     * @return the price of this Item instance
     * @return
     */
    float getPrice();

    /**
     * @return list of colours the Item is available in.
     */
    List<String> getColour();

    /**
     * @return the full qualified name of the first image of this Item instance
     */
    String getFirstImage();

    /**
     * @return the full qualified name of the material image of this Item instance
     */
    String getMaterialType();

    /**
     * @return the list of all full qualified names of the images of this item instance (ignore the
     * material image)
     */
    List<String> getImage();

    /**
     * @return the string description of this Item
     */
    String getDescription();

    /**
     * @return the view count of this Item instance
     */
    int getViewCount();

    /**
     * Increase the view count of this image by 1
     */
    void incrementViewCount();

    /**
     * Reset the view count of this image to 0
     */
    void resetViewCount();

    /**
     * @return if a Handle object is lockable or not
     */
    boolean getLockable();

    /**
     * Sets lockable status to true or false
     * @param lockStatus Boolean of lock status
     */
    void setLockable(boolean lockStatus);

    /**
     * @return the string representing the type of lock for a Handle object
     */
    String getLockType();

    /**
     * Sets lock type to corresponding String
     * @param lock String of lock type
     */
    void setLockType(String lock);

    /**
     * @return list of dimensions of the items (height, length, width)
     */
    List<Long> getDimensions();

    /**
     * Sets description of item
     * @param newDescription
     */
    void setDescription(String newDescription);

    /**
     * @return weight of Item instance.
     */
    int getWeight();

    /**
     * @return list of categories that the item is a member of
     */
    List<String> getCategories();
}
