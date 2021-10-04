package com.example.listapp.model;

import java.util.List;

public interface IDataLoader {

    public void initialiseData();

    public List<Item> getItemsByString(String searchString);

    public List<Item> getItemsByCriteria(String categoryName);

    public Item getItemByName(String itemName);

    public List<Item> sortItemListByViewCount();

    public void persistData();

}
