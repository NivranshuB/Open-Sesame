package com.example.listapp.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    private CollectionReference handleRef = db.collection("handles");


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
    public void getItemsByString(String matchString, DataCallback callback) {
        String[] matchList = matchString.split("\\s+");
        List<Item> resultList = new ArrayList<>();

        doorRef.whereArrayContainsAny("name", Arrays.asList(matchList)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Map<String, Object> currObj = curr.getData();
                    for (String objType : (List<String>) currObj.get("categories")) {
                        Item door = null;
                        if (objType.equals(DOOR_TYPES[0])) {
                            door = curr.toObject(MetalDoor.class);
                        } else if (objType.equals(DOOR_TYPES[1])) {
                            door = curr.toObject(GlassDoor.class);
                        } else if (objType.equals(DOOR_TYPES[2])) {
                            door = curr.toObject(WoodenDoor.class);
                        }
                        resultList.add(door);
                    }
                    callback.dataListCallback(resultList);
                }
            }
        });
    }


    @Override
    public void getItemsByCriteria(String categoryName, DataCallback callback) {
        List<String> categoryList = new ArrayList<>();
        categoryList.add(categoryName);
        List<Item> resultList = new ArrayList<>();

        doorRef.whereArrayContainsAny("categories", categoryList).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Map<String, Object> currObj = curr.getData();
                    StringBuilder mapAsString = new StringBuilder("{");
                    for (String key : currObj.keySet()) {
                        mapAsString.append(key + "=" + currObj.get(key) + ", ");
                    }
                    mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
                    Log.d("mapString", mapAsString.toString());
                    for (String objType : (List<String>) currObj.get("categories")) {
                        Item door = null;
                        if (objType.equals(DOOR_TYPES[0])) {
                            door = curr.toObject(MetalDoor.class);
                        } else if (objType.equals(DOOR_TYPES[1])) {
                            door = curr.toObject(GlassDoor.class);
                        } else if (objType.equals(DOOR_TYPES[2])) {
                            door = curr.toObject(WoodenDoor.class);
                        }
                        resultList.add(door);
                    }
                    callback.dataListCallback(resultList);
                }
            }
        });
    }

    @Override
    public void getItemByName(String itemName, DataCallback callback) {
        //todo
    }

    public void getItemByID(int id, DataCallback callback) {
        final Item[] item = new Item[1];
        doorRef.whereEqualTo("id", id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Map<String, Object> currObj = queryDocumentSnapshots.getDocuments().get(0).getData();
                StringBuilder mapAsString = new StringBuilder("{");
                for (String key : currObj.keySet()) {
                    mapAsString.append(key + "=" + currObj.get(key) + ", ");
                }
                mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
                Log.d("mapString", mapAsString.toString());
                MetalDoor i = queryDocumentSnapshots.getDocuments().get(0).toObject(MetalDoor.class);
                callback.itemCallback(i);
                item[0] = i;
            }
        });
    }

    @Override
    public void sortItemListByViewCount(DataCallback callback) {
        //todo
        List<Item> resultList = new ArrayList<>();

        doorRef.orderBy("viewCount", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Map<String, Object> currObj = curr.getData();
                    for (String objType : (List<String>) currObj.get("categories")) {
                        Item door = null;
                        if (objType.equals(DOOR_TYPES[0])) {
                            door = curr.toObject(MetalDoor.class);
                        } else if (objType.equals(DOOR_TYPES[1])) {
                            door = curr.toObject(GlassDoor.class);
                        } else if (objType.equals(DOOR_TYPES[2])) {
                            door = curr.toObject(WoodenDoor.class);
                        }
                        resultList.add(door);
                    }
                    callback.dataListCallback(resultList);
                }
            }
        });
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

// Implementation to print entire map as string (for debugging purposes)
//    Map<String, Object> currObj = queryDocumentSnapshots.getDocuments().get(0).getData();
//    StringBuilder mapAsString = new StringBuilder("{");
//                for (String key : currObj.keySet()) {
//                        mapAsString.append(key + "=" + currObj.get(key) + ", ");
//                        }
//                        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
//                        Log.d("mapString", mapAsString.toString());

//                MetalDoor door = new MetalDoor(Math.toIntExact((Long) currObj.get("id")), Math.toIntExact((Long) currObj.get("weight")), Math.toIntExact((Long) currObj.get("viewCount")), ((Long)currObj.get("price")).floatValue(),
//                        (List<Long>) currObj.get("dimensions"), (List<String>) currObj.get("name"), (String) currObj.get("description"),
//                        (List<String>) currObj.get("colour"), (List<String>) currObj.get("image"));