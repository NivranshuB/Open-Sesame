package com.example.listapp.model;

import java.util.List;

/**
 * Open Sesame
 *
 * This interface needs to be implemented directly by the class that will take on the responsibility
 * of all the data retrievals from the database. As long as these methods are implemented as
 * specified below, the user interface class will be compatible with any database used by the
 * application for storage.
 */
public interface IDataLoader {

    /**
     * This method retrieves all the items stored in the database and stores them in a list.
     * (Potentially not needed if every retrieval is straight from the database).
     */
    public void initialiseData();

    /**
     * This method retrieves all the items from the database that contain the input string in their
     * name, ignoring the casing of the input string.
     *
     * @param matchString: string that we are looking for
     * @return List of matching items
     *         If not matches return 'null'
     */
    public List<Item> getItemsByString(String matchString, DataCallback callback);

    /**
     * This method retrieves all the items from the database that belong to the same category as
     * that specified in the input string.
     *
     * @param categoryName: name of a category
     * @return List of matching items from the specified category
     *         If no matches return 'null'
     */
    public List<Item> getItemsByCriteria(String categoryName, DataCallback callback);

    /**
     * This method retrieves a single item from the database which matches the name specified in the
     * input, ignoring the casing of the input string.
     *
     * @param itemName: Name of item to retrieve
     * @return Single item that has the name specified
     *         If no matches return 'null'
     */
    public Item getItemByName(String itemName, DataCallback callback);

    /**
     * This method retrieves all items from the database, sorts the items in decreasing order of
     * with respect to the view count and then returns this sorted list of items.
     *
     * @return Sorted list (by view count) of all items in the database
     */
    public List<Item> sortItemListByViewCount(DataCallback callback);

    /**
     * When a change is made to any item that needs to be persisted calling this method will persist
     * that change to the database.
     */
    public void persistData(Item itemChanged);

}
