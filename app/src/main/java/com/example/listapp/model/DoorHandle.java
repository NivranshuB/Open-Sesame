package com.example.listapp.model;

import java.util.List;

public class DoorHandle extends Handle {


    @Override
    public List<String> getImage() {
        return image;
    }

    @Override
    public List<Long> getDimensions() {
        return dimensions;
    }
}
