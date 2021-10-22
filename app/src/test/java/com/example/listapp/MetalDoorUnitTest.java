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

/**
 * This test suite handles all test cases which apply to a 'MetalDoor' item specifically.
 */
public class MetalDoorUnitTest {

    Item metalDoor;

    /**
     * Create the MetalDoor instance that will be used for testing.
     */
    @Before
    public void setup() {
        List<Long> dimensions = new ArrayList<>();
        dimensions.add(1600L);
        dimensions.add(600L);
        dimensions.add(36L);

        List<String> nameMetalDoor = new ArrayList<>();
        nameMetalDoor.add("Thin");
        nameMetalDoor.add("Metal");
        nameMetalDoor.add("Door");

        List<String> colour = new ArrayList<>();
        colour.add("#000000");
        colour.add("#ffffff");
        colour.add("#00ff00");

        List<String> metalDoorImages = new ArrayList<>();
        metalDoorImages.add("door27_1");
        metalDoorImages.add("door27_2");
        metalDoorImages.add("door27_3");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum";

        metalDoor = new MetalDoor(2, 200, 90, 80.05f, dimensions,
                nameMetalDoor, description, colour, metalDoorImages);
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
     * Test that the material type attribute of a MetalDoor can be set correctly and then
     * accessed as expected.
     */
    @Test
    public void TestMaterialTypeMetalDoor() {
        MetalDoor metal = new MetalDoor();
        metal = (MetalDoor) metalDoor;

        metal.setMaterialType("Aluminium");

        assertEquals("Aluminium", metal.getMaterialType());
    }
}
