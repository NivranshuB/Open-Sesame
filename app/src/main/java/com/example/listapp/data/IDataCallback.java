package com.example.listapp.data;

import com.example.listapp.model.Item;

import java.util.List;

/**
 * Interface that classes must utilize when using any of the data querying methods of the DataLoader
 * interface. This dataListCallback method is called by DataLoader when all the items specified in
 * the query have been retrieved while the itemCallback method is called when a single item from the
 * query has been retrieved.
 */
public interface IDataCallback {

    void dataListCallback(List<Item> itemList);

    void itemCallback(Item item);

}
