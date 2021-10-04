package com.example.listapp.model;

import java.util.List;

/**
 * Open Sesame
 *
 * This class implements the responsibilities as specified by the IDataLoader interface. This class
 * performs data retrievals and also data persistence to and from the application's firestore
 * database.
 */
public class DataLoader implements IDataLoader {

    @Override
    public void initialiseData() {
        //todo
    }

    @Override
    public List<Item> getItemsByString(String matchString) {
        //todo
        return null;
    }

    @Override
    public List<Item> getItemsByCriteria(String categoryName) {
        //todo
        return null;
    }

    @Override
    public Item getItemByName(String itemName) {
        //todo
        return null;
    }

    @Override
    public List<Item> sortItemListByViewCount() {
        //todo
        return null;
    }

    @Override
    public void persistData(Item itemChanged) {
        //todo
    }
}
