package com.example.listapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.listapp.model.DoorHandle;
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Item;
import com.example.listapp.model.MetalDoor;
import com.example.listapp.model.WoodenDoor;

import java.util.ArrayList;
import java.util.List;

public class ItemUnitTest {

    Item woodenDoor, glassDoor, metalDoor, doorHandle;

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

        List<String> nameMetalDoor = new ArrayList<>();
        nameMetalDoor.add("Thin");
        nameMetalDoor.add("Metal");
        nameMetalDoor.add("Door");

        List<String> nameGlassDoor = new ArrayList<>();
        nameGlassDoor.add("Strong");
        nameGlassDoor.add("Blue");
        nameGlassDoor.add("Glass");
        nameGlassDoor.add("Door");

        List<String> nameDoorHandle = new ArrayList<>();
        nameDoorHandle.add("Sleek");
        nameDoorHandle.add("Golden");
        nameDoorHandle.add("Door");
        nameDoorHandle.add("Handle");

        List<String> colour = new ArrayList<>();
        colour.add("#000000");
        colour.add("#ffffff");
        colour.add("#00ff00");

        List<String> woodenDoorImages = new ArrayList<>();
        woodenDoorImages.add("door21_1");
        woodenDoorImages.add("door21_2");
        woodenDoorImages.add("door21_3");

        List<String> metalDoorImages = new ArrayList<>();
        metalDoorImages.add("door27_1");
        metalDoorImages.add("door27_2");
        metalDoorImages.add("door27_3");

        List<String> glassDoorImages = new ArrayList<>();
        glassDoorImages.add("door14_1");
        glassDoorImages.add("door14_2");
        glassDoorImages.add("door14_3");

        List<String> doorHandleImages = new ArrayList<>();
        glassDoorImages.add("handle11_1");
        glassDoorImages.add("handle11_2");
        glassDoorImages.add("handle11_3");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum";

        woodenDoor = new WoodenDoor(1, 300, 110, 50.05f, dimensions,
                nameWoodenDoor, description, colour, woodenDoorImages);

        metalDoor = new MetalDoor(2, 200, 90, 80.05f, dimensions,
                nameMetalDoor, description, colour, metalDoorImages);

        glassDoor = new GlassDoor(3, 100, 170, 30.05f, dimensions,
                nameGlassDoor, description, colour, glassDoorImages);

        doorHandle = new DoorHandle(4, 30, 390, 80.60f, dimensions,
                nameDoorHandle, description, colour, doorHandleImages, false);
    }

    /**
     * Check the construction of a WoodenDoor instance and if it has all the relevant attributes
     * that an Item must have.
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
     *
     */
    @Test
    public void TestWoodenDoorReferenceSizes() {
        assertEquals(3, woodenDoor.getDimensions().size());
        assertEquals(4, woodenDoor.getName().size());
        assertEquals(3, woodenDoor.getImage().size());
    }

    /**
     * Check the construction of a WoodenDoor instance and if it has all the relevant attributes
     * that an Item must have.
     */
    @Test
    public void TestWoodenDoorReferences() {
        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        for (int i = 0; i < woodenDoor.getDimensions().size(); i++) {
            assertEquals(woodenDoor.getDimensions().get(i), dimensions.get(i));
        }
    }

    /**
     * Check the construction of a MetalDoor instance and if it has all the relevant attributes
     * that an Item must have.
     */
    @Test
    public void TestMetalDoorPrimitives() {
        assertEquals(2, metalDoor.getId());
        assertEquals(200, metalDoor.getWeight());
        assertEquals(90, metalDoor.getViewCount());
        assertEquals(80.05f, metalDoor.getPrice(),1);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum",
                metalDoor.getDescription());
    }

    /**
     *
     */
    @Test
    public void TestMetalDoorReferenceSizes() {
        assertEquals(metalDoor.getDimensions().size(), 3);
        assertEquals(metalDoor.getName().size(), 3);
        assertEquals(metalDoor. getImage().size(), 3);
    }

    /**
     * Check the construction of a GlassDoor instance and if it has all the relevant attributes
     * that an Item must have.
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
     *
     */
    @Test
    public void TestGlassDoorReferenceSizes() {
        assertEquals(glassDoor.getDimensions().size(), 3);
        assertEquals(glassDoor.getName().size(), 4);
        assertEquals(glassDoor. getImage().size(), 3);
    }

    /**
     * Check the construction of a DoorHandle instance and if it has all the relevant attributes
     * that an Item must have.
     */
    @Test
    public void TestHandlePrimitives() {
        assertEquals(4, doorHandle.getId());
        assertEquals(30, doorHandle.getWeight());
        assertEquals(390, doorHandle.getViewCount());
        assertEquals(80.60f, doorHandle.getPrice(),1);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum",
                doorHandle.getDescription());
    }

    /**
     * Check the conversion of a Door subtype instance to a Handle subtype instance.
     */
    @Test
    public void TestDoorToHandle() {

    }

    /**
     * Check the conversion of a Handle subtype instance to a Door subtype instance.
     */
    @Test
    public void TestHandleToDoor() {

    }

    /**
     * Check how Handle instances deal with having Door type responsibilities invoked on
     * them.
     */
    @Test
    public void TestSpecialDoorAttributesOnHandle() {

    }

    /**
     * Check how Door instances deal with having Handle type responsibilities invoked on them.
     */
    @Test
    public void TestSpecialHandleAttributesOnDoor() {

    }

}
