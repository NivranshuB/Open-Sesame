package com.example.listapp.model;

import java.util.List;

public interface DataCallback {

    void dataListCallback(List<Item> itemList);

    void itemCallback(Item item);

}
