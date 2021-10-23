package com.example.listapp.data;

import com.example.listapp.model.Item;

import java.util.List;

public interface IDataCallback {

    void dataListCallback(List<Item> itemList);

    void itemCallback(Item item);

}
