package com.example.listapp.model;

import java.util.List;

public class DoorHandle extends Handle {

    public DoorHandle() {

    }

    public DoorHandle(int id, int weight, int viewCount, float price,
                     List<Long> dimensions, List<String> name, String description,
                     List<String> colour, List<String> image, Boolean lockable) {
        this.id = id;
        this.weight = weight;
        this.viewCount = viewCount;
        this.price = price;
        this.dimensions = dimensions;
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.image = image;
        this.lockable = lockable;
    }
}
