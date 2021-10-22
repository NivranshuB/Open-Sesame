package com.example.listapp;

import static org.junit.Assert.assertEquals;

import com.example.listapp.model.DoorHandle;
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Item;
import com.example.listapp.model.MetalDoor;
import com.example.listapp.model.WoodenDoor;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WoodenDoorUnitTest {

    Item woodenDoor;

    @Before
    public void setup() {
        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        List<String> nameWoodenDoor = new ArrayList<>();
        nameWoodenDoor.add("Large");
        nameWoodenDoor.add("Timber");
        nameWoodenDoor.add("Wooden");
        nameWoodenDoor.add("Door");

        List<String> colour = new ArrayList<>();
        colour.add("#000000");
        colour.add("#ffffff");
        colour.add("#00ff00");

        List<String> woodenDoorImages = new ArrayList<>();
        woodenDoorImages.add("door21_1");
        woodenDoorImages.add("door21_2");
        woodenDoorImages.add("door21_3");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum";

        woodenDoor = new WoodenDoor(1, 300, 110, 50.05f, dimensions,
                nameWoodenDoor, description, colour, woodenDoorImages);
    }

    /**
     * Check the construction of a WoodenDoor instance and if it has all the relevant primitive
     * attributes that an Item must have.
     */
    @Test
    public void TestWoodenDoorPrimitives() {
        assertEquals(1, woodenDoor.getId());
        assertEquals(300, woodenDoor.getWeight());
        assertEquals(110, woodenDoor.getViewCount());
        assertEquals(50.05f, woodenDoor.getPrice(),1);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum",
                woodenDoor.getDescription());
    }

    /**
     * Check the construction of a WoodenDoor instance and if the sizes of its list attributes are
     * as to be expected.
     */
    @Test
    public void TestWoodenDoorReferenceSizes() {
        assertEquals(3, woodenDoor.getDimensions().size());
        assertEquals(4, woodenDoor.getName().size());
        assertEquals(3, woodenDoor.getImage().size());
    }

    /**
     * Check the construction of a WoodenDoor instance and if it has all the relevant reference
     * (list) attributes that an Item must have.
     */
    @Test
    public void TestWoodenDoorReferences() {
        List<String> nameWoodenDoor = new ArrayList<>();
        nameWoodenDoor.add("Large");
        nameWoodenDoor.add("Timber");
        nameWoodenDoor.add("Wooden");
        nameWoodenDoor.add("Door");

        for (int i = 0; i < woodenDoor.getName().size(); i++) {
            assertEquals(nameWoodenDoor.get(i), woodenDoor.getName().get(i));
        }

        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        for (int i = 0; i < woodenDoor.getDimensions().size(); i++) {
            assertEquals(dimensions.get(i), woodenDoor.getDimensions().get(i));
        }

        List<String> woodenDoorImages = new ArrayList<>();
        woodenDoorImages.add("door21_1");
        woodenDoorImages.add("door21_2");
        woodenDoorImages.add("door21_3");

        for (int i = 0; i < woodenDoor.getImage().size(); i++) {
            assertEquals(woodenDoorImages.get(i), woodenDoor.getImage().get(i));
        }
    }

    /**
     *
     */
    @Test
    public void TestMaterialTypeWoodenDoor() {
        WoodenDoor woodDoor = new WoodenDoor();
        woodDoor = (WoodenDoor) woodenDoor;

        woodDoor.setMaterialType("Timber");

        assertEquals("Timber", woodDoor.getMaterialType());
    }
}
