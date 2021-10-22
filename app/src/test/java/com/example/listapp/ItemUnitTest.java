package com.example.listapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.listapp.model.Door;
import com.example.listapp.model.DoorHandle;
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Handle;
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
     *
     */
    @Test
    public void TestExclusiveDoorAttributes() {
        assertNull(woodenDoor.getFirestoreID());
    }

    /**
     * Check how Handle instances deal with having Door type responsibilities invoked on
     * them.
     */
    @Test
    public void TestSpecialDoorAttributesOnHandle() {
        assertEquals("", doorHandle.getMaterialType());
    }

    /**
     * Check how Door instances deal with having Handle type responsibilities invoked on them.
     */
    @Test
    public void TestSpecialHandleAttributesOnDoor() {
        assertEquals(false, woodenDoor.getLockable());
        assertEquals("", woodenDoor.getLockType());
    }

    /**
     * Test if the view count of an item gets incremented correctly or not.
     */
    @Test
    public void TestViewCountIncrement() {
        metalDoor.resetViewCount();
        for (int i = 0; i < 10; i++) {
            metalDoor.incrementViewCount();
        }

        assertEquals(10, metalDoor.getViewCount());
    }

    /**
     * Test if the view count of an item get reset correctly or not.
     */
    @Test
    public void TestViewCountReset() {
        for (int i = 0; i < 10; i++) {
            glassDoor.incrementViewCount();
        }
        glassDoor.resetViewCount();

        assertEquals(0, glassDoor.getViewCount());
    }

}
