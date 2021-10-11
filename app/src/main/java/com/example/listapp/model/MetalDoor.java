package com.example.listapp.model;

/**
 * Open Sesame
 *
 * -Responsibilities of a metal door...
 */
public class MetalDoor extends Door {

    String materialType;

    /**
     * @return the full qualified name of the material image of this Item instance
     */
    @Override
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String newMaterialType) {
        materialType = newMaterialType;
    }
}
