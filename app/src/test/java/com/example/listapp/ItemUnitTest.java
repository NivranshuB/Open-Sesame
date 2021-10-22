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
        doorHandleImages.add("handle11_1");
        doorHandleImages.add("handle11_2");
        doorHandleImages.add("handle11_3");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum";

        woodenDoor = new WoodenDoor(1, 300, 110, 50.05f, dimensions,
                nameWoodenDoor, description, colour, woodenDoorImages);

        metalDoor = new MetalDoor(2, 200, 90, 80.05f, dimensions,
                nameMetalDoor, description, colour, metalDoorImages);

        glassDoor = new GlassDoor(3, 100, 170, 30.05f, dimensions,
                nameGlassDoor, description, colour, glassDoorImages);

        doorHandle = new DoorHandle(4, 30, 390, 80.60f, null,
                nameDoorHandle, description, colour, doorHandleImages, false);
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
     * Check the construction of a MetalDoor instance and if it has all the relevant primitive
     * attributes that an Item must have.
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
     * Check the construction of a MetalDoor instance and if the sizes of its list attributes are
     * as to be expected.
     */
    @Test
    public void TestMetalDoorReferenceSizes() {
        assertEquals(3, metalDoor.getDimensions().size());
        assertEquals(3, metalDoor.getName().size());
        assertEquals(3, metalDoor.getImage().size());
    }

    /**
     * Check the construction of a MetalDoor instance and if it has all the relevant reference
     * (list) attributes that an Item must have.
     */
    @Test
    public void TestMetalDoorReferences() {
        List<String> nameMetalDoor = new ArrayList<>();
        nameMetalDoor.add("Thin");
        nameMetalDoor.add("Metal");
        nameMetalDoor.add("Door");

        for (int i = 0; i < metalDoor.getName().size(); i++) {
            assertEquals(nameMetalDoor.get(i), metalDoor.getName().get(i));
        }

        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        for (int i = 0; i < metalDoor.getDimensions().size(); i++) {
            assertEquals(dimensions.get(i), metalDoor.getDimensions().get(i));
        }

        List<String> metalDoorImages = new ArrayList<>();
        metalDoorImages.add("door27_1");
        metalDoorImages.add("door27_2");
        metalDoorImages.add("door27_3");

        for (int i = 0; i < metalDoor.getImage().size(); i++) {
            assertEquals(metalDoorImages.get(i), metalDoor.getImage().get(i));
        }
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
     * Check the construction of a DoorHandle instance and if it has all the relevant primitive
     * attributes that an Item must have.
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
     * Check the construction of a DoorHandle instance and if the sizes of its list attributes are
     * as to be expected. Note the dimensions of a door handle will be null since this attribute
     * does not apply to door handles.
     */
    @Test
    public void TestHandleReferenceSizes() {
        assertNull(doorHandle.getDimensions());
        assertEquals(4, doorHandle.getName().size());
        assertEquals(3, doorHandle.getImage().size());
    }

    /**
     * Check the construction of a DoorHandle instance and if it has all the relevant reference
     * (list) attributes that an Item must have barring the dimensions attribute.
     */
    @Test
    public void TestHandleReferences() {
        List<String> nameDoorHandle = new ArrayList<>();
        nameDoorHandle.add("Sleek");
        nameDoorHandle.add("Golden");
        nameDoorHandle.add("Door");
        nameDoorHandle.add("Handle");

        for (int i = 0; i < doorHandle.getName().size(); i++) {
            assertEquals(nameDoorHandle.get(i), doorHandle.getName().get(i));
        }

        List<String> doorHandleImages = new ArrayList<>();
        doorHandleImages.add("handle11_1");
        doorHandleImages.add("handle11_2");
        doorHandleImages.add("handle11_3");

        for (int i = 0; i < doorHandle.getImage().size(); i++) {
            assertEquals(doorHandleImages.get(i), doorHandle.getImage().get(i));
        }
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
