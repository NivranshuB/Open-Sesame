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

/**
 * This test suite handles all test cases which apply to a 'Door' or 'Handle' subtype. Contrary to
 * the specific unit test suites this class tests the general functionality of an Item instance.
 */
public class ItemUnitTest {

    Item woodenDoor, glassDoor, metalDoor, doorHandle;

    /**
     * Method to create the different types of doors that will be used for testing in this suite.
     */
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
     * This test case checks that a Door type object behaves appropriately when it is created with
     * the default constructor and its fields are populated manually through its setter methods.
     */
    @Test
    public void TestManuallySettingDoorAttributes() {
        WoodenDoor wood = new WoodenDoor();
        wood.setFirestoreID("6");
        assertEquals("6", wood.getFirestoreID());

        List<String> newName = new ArrayList<>();
        newName.add("Smoked");
        newName.add("Oak");
        newName.add("Door");

        wood.setName(newName);

        List<String> newImages = new ArrayList<>();
        newImages.add("Door10_1");
        newImages.add("Door10_2");

        wood.setImages(newImages);

        wood.setDescription("Hello");

        assertEquals(3, wood.getName().size());
        assertEquals(2, wood.getImage().size());
        assertEquals("Door10_1", wood.getFirstImage());
        assertEquals("Hello", wood.getDescription());
        assertNull(wood.getColour());
        assertNull(wood.getCategories());
    }

    /**
     * This test case checks that a Handle type object behaves appropriately when it is created with
     * the default constructor and its fields are populated manually through its setter methods.
     */
    @Test
    public void TestManuallySettingHandleAttributes() {
        DoorHandle handle = new DoorHandle();
        handle.setFirestoreID("7");
        assertEquals("7", handle.getFirestoreID());

        handle.setLockType("Hinge");
        handle.setLockable(true);

        List<String> newImages = new ArrayList<>();
        newImages.add("Handle5_1");
        newImages.add("Handle5_2");

        handle.setImage(newImages);

        handle.setDescription("Hello");

        assertEquals("Hinge", handle.getLockType());
        assertTrue(handle.getLockable());

        assertEquals(2, handle.getImage().size());
        assertEquals("Handle5_1", handle.getFirstImage());
        assertEquals("Hello", handle.getDescription());
        assertEquals("handle", handle.getCategories().get(0));
    }

    /**
     * This test case checks that the getMaterialType() function, when invoked on a glass door
     * returns an empty string since a glass door does not have the a material type attribute.
     */
    @Test
    public void TestMaterialTypeAttribute() {
        assertEquals("", glassDoor.getMaterialType());
    }

    /**
     * Checks how Handle instances deal with having Door type responsibilities invoked on
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
     * Tests if the view count of a Door type item gets incremented correctly or not.
     */
    @Test
    public void TestViewCountIncrementDoor() {
        metalDoor.resetViewCount();
        for (int i = 0; i < 10; i++) {
            metalDoor.incrementViewCount();
        }

        assertEquals(10, metalDoor.getViewCount());
    }

    /**
     * Test if the view count of a Door type item get reset correctly or not.
     */
    @Test
    public void TestViewCountResetDoor() {
        for (int i = 0; i < 10; i++) {
            glassDoor.incrementViewCount();
        }
        glassDoor.resetViewCount();

        assertEquals(0, glassDoor.getViewCount());
    }

    /**
     * Tests if the view count of a Handle type item gets incremented correctly or not.
     */
    @Test
    public void TestViewCountIncrementHandle() {
        doorHandle.resetViewCount();
        for (int i = 0; i < 10; i++) {
            doorHandle.incrementViewCount();
        }

        assertEquals(10, doorHandle.getViewCount());
    }

    /**
     * Test if the view count of a Handle type item get reset correctly or not.
     */
    @Test
    public void TestViewCountResetHandle() {
        for (int i = 0; i < 10; i++) {
            doorHandle.incrementViewCount();
        }
        doorHandle.resetViewCount();

        assertEquals(0, doorHandle.getViewCount());
    }

}
