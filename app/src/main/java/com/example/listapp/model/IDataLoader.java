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
     * This method retrieves all the items from the database that contain the input string in their
     * name, ignoring the casing of the input string.
     *
     * @param matchString: string that we are looking for
     * @return List of matching items
     *         If not matches return 'null'
     */
    public void getItemsByName(String matchString, IDataCallback callback);

    /**
     * This method retrieves all the items from the database that belong to the same category as
     * that specified in the input string.
     *
     * @param categoryName: name of a category
     * @return List of matching items from the specified category
     *         If no matches return 'null'
     */
    public void getItemsByCriteria(String categoryName, IDataCallback callback);


    /**
     * This method retrieves all items from the database, sorts the items in decreasing order of
     * with respect to the view count and then returns this sorted list of items.
     *
     * @return Sorted list (by view count) of all items in the database
     */
    public void sortItemListByViewCount(IDataCallback callback);

    /**
     * When a change is made to any item that needs to be persisted calling this method will persist
     * that change to the database.
     */
    public void persistData(Item itemChanged);

    /**
     * This method retrieves a single item from the database which matches the id specified in the
     * input.
     *
     * @param id: Id of item to retrieve from database
     * @return Single item that has the ID specified
     *         If no matches return 'null'
     */
    public void getItemByID(int id, IDataCallback callback);

    /**
     * This method retrieves multiple objects based on the IDs supplied in List format.
     * @param idList IDs of the items to retrieve from the database
     * @param callback Multiple items that have an ID that matches one of the supplied IDs.
     */
    public void getItemsByID(List<Integer> idList, IDataCallback callback);

}
