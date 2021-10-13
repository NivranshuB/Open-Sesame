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
     * @return the name of this Item instance
     */
    List<String> getName();

    /**
     * @return the price of this Item instance
     * @return
     */
    float getPrice();

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
     * @return the string representing the type of lock for a Handle object
     */
    String getLockType();

    List<Integer> getDimensions();
}
