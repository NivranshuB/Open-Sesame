package com.example.listapp.model;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Open Sesame
 *
 * This class implements the responsibilities as specified by the IDataLoader interface. This class
 * performs data retrievals and also data persistence to and from the application's firestore
 * database.
 */
public class DataLoader implements IDataLoader {

    private HashMap<String, ArrayList<Item>> doorMap = new HashMap<>();
    private String[] DOOR_TYPES = {"metallic", "glass", "wooden"};
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference doorRef = db.collection("door");


    /**
     * Initialise the client's copy of the database's documents. Currently a naive solution
     * that basically retrieves every document from the database.
     */
    @Override
    public void initialiseData() {
        initialiseMap();

        // Store doors if the query was successful.
        doorRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Loop through all documents returned. In this implementation, this is all documents in door collection.
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Map<String, Object> currObj = curr.getData();
                    for (String objType : (List<String>)currObj.get("categories")) {
                        if (objType.equals(DOOR_TYPES[0])) {
                            MetalDoor door = curr.toObject(MetalDoor.class);
                            doorMap.get(DOOR_TYPES[0]).add(door);
                        } else if (objType.equals(DOOR_TYPES[1])) {
                            GlassDoor door = curr.toObject(GlassDoor.class);
                            doorMap.get(DOOR_TYPES[1]).add(door);
                        } else if (objType.equals(DOOR_TYPES[2])) {
                            WoodenDoor door = curr.toObject(WoodenDoor.class);
                            doorMap.get(DOOR_TYPES[2]).add(door);
                        }

                    }
                }
            }
        });
    }

    @Override
    public List<Item> getItemsByString(String matchString) {
        //todo
        return null;
    }

    @Override
    public List<Item> getItemsByCriteria(String categoryName) {
        //todo
        return null;
    }

    @Override
    public Item getItemByName(String itemName) {
        //todo
        return null;
    }

    @Override
    public List<Item> sortItemListByViewCount() {
        //todo
        return null;
    }

    @Override
    public void persistData(Item itemChanged) {
        //todo
    }

    /**
     * Initialise the maps to store data.
     */
    private void initialiseMap() {

        for (String doorType : DOOR_TYPES) {
            doorMap.put(doorType, new ArrayList<Item>());
        }

    }
}
