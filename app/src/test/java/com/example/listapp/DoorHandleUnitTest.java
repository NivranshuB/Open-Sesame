package com.example.listapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.listapp.model.DoorHandle;
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Item;
import com.example.listapp.model.MetalDoor;
import com.example.listapp.model.WoodenDoor;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This test suite handles all test cases which apply to a 'DoorHandle' item specifically.
 */
public class DoorHandleUnitTest {

    Item doorHandle;

    /**
     * Create the DoorHandle instance that will be used for testing.
     */
    @Before
    public void setup() {
        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        List<String> nameDoorHandle = new ArrayList<>();
        nameDoorHandle.add("Sleek");
        nameDoorHandle.add("Golden");
        nameDoorHandle.add("Door");
        nameDoorHandle.add("Handle");

        List<String> colour = new ArrayList<>();
        colour.add("#000000");
        colour.add("#ffffff");
        colour.add("#00ff00");

        List<String> doorHandleImages = new ArrayList<>();
        doorHandleImages.add("handle11_1");
        doorHandleImages.add("handle11_2");
        doorHandleImages.add("handle11_3");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum";

        doorHandle = new DoorHandle(4, 30, 390, 80.60f, null,
                nameDoorHandle, description, colour, doorHandleImages, false);
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
}
