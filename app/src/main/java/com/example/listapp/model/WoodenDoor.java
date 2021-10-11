package com.example.listapp.model;

/**
 * Open Sesame
 *
 * -Responsibilities of a wooden door...
 */
public class WoodenDoor extends Door {

    String materialType;

    /**
     * @return the full qualified name of the material image of this Item instance
     */
    @Override
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialImage(String newMaterialType) {
        materialType = newMaterialType;
    }
}
