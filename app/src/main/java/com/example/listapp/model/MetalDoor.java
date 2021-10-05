package com.example.listapp.model;

/**
 * Open Sesame
 *
 * -Responsibilities of a metal door...
 */
public class MetalDoor extends Door {

    String materialImage;

    /**
     * @return the full qualified name of the material image of this Item instance
     */
    @Override
    public String getMaterialImage() {
        return null;
    }

    public void setMaterialImage(String newMaterialImage) {
        materialImage = newMaterialImage;
    }
}
