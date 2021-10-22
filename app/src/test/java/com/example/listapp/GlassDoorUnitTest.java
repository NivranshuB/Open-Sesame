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

public class GlassDoorUnitTest {

    Item glassDoor;

    @Before
    public void setup() {
        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        List<String> nameGlassDoor = new ArrayList<>();
        nameGlassDoor.add("Strong");
        nameGlassDoor.add("Blue");
        nameGlassDoor.add("Glass");
        nameGlassDoor.add("Door");

        List<String> colour = new ArrayList<>();
        colour.add("#000000");
        colour.add("#ffffff");
        colour.add("#00ff00");

        List<String> glassDoorImages = new ArrayList<>();
        glassDoorImages.add("door14_1");
        glassDoorImages.add("door14_2");
        glassDoorImages.add("door14_3");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum";

        glassDoor = new GlassDoor(3, 100, 170, 30.05f, dimensions,
                nameGlassDoor, description, colour, glassDoorImages);
    }

    /**
     * Check the construction of a GlassDoor instance and if it has all the relevant primitive
     * attributes that an Item must have.
     */
    @Test
    public void TestGlassDoorPrimitives() {
        assertEquals(3, glassDoor.getId());
        assertEquals(100, glassDoor.getWeight());
        assertEquals(170, glassDoor.getViewCount());
        assertEquals(30.05f, glassDoor.getPrice(),1);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum",
                glassDoor.getDescription());
    }

    /**
     * Check the construction of a GlassDoor instance and if the sizes of its list attributes are
     * as to be expected.
     */
    @Test
    public void TestGlassDoorReferenceSizes() {
        assertEquals(3, glassDoor.getDimensions().size());
        assertEquals(4, glassDoor.getName().size());
        assertEquals(3, glassDoor.getImage().size());
    }

    /**
     * Check the construction of a GlassDoor instance and if it has all the relevant reference
     * (list) attributes that an Item must have.
     */
    @Test
    public void TestGlassDoorReferences() {
        List<String> nameGlassDoor = new ArrayList<>();
        nameGlassDoor.add("Strong");
        nameGlassDoor.add("Blue");
        nameGlassDoor.add("Glass");
        nameGlassDoor.add("Door");

        for (int i = 0; i < glassDoor.getName().size(); i++) {
            assertEquals(nameGlassDoor.get(i), glassDoor.getName().get(i));
        }

        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        for (int i = 0; i < glassDoor.getDimensions().size(); i++) {
            assertEquals(dimensions.get(i), glassDoor.getDimensions().get(i));
        }

        List<String> glassDoorImages = new ArrayList<>();
        glassDoorImages.add("door14_1");
        glassDoorImages.add("door14_2");
        glassDoorImages.add("door14_3");

        for (int i = 0; i < glassDoor.getImage().size(); i++) {
            assertEquals(glassDoorImages.get(i), glassDoor.getImage().get(i));
        }
    }

    /**
     *
     */
    @Test
    public void TestGlassDoorEmptyConstructor() {
        GlassDoor glass = new GlassDoor();
        glass = (GlassDoor) glassDoor;

        assertEquals(3 ,glass.getId());
    }
}
