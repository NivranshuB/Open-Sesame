package com.example.listapp.model;

import java.util.List;

public interface IDataCallback {

    void dataListCallback(List<Item> itemList);

    void itemCallback(Item item);

}
