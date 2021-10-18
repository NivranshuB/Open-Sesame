package com.example.listapp.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
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
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Open Sesame
 * <p>
 * This class implements the responsibilities as specified by the IDataLoader interface. This class
 * performs data retrievals and also data persistence to and from the application's firestore
 * database.
 */
public class DataLoader implements IDataLoader {

    private HashMap<String, ArrayList<Item>> doorMap = new HashMap<>();
    private String[] DOOR_TYPES = {"metallic", "glass", "wooden"};
    private String HANDLE_TYPE = "handle";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference doorRef = db.collection("door");
    private CollectionReference handleRef = db.collection("handle");


    /**
     * Initialise the client's copy of the database's documents. Currently a naive solution
     * that basically retrieves every document from the database.
     */
    @Override
    public void initialiseData() {

        // Store doors if the query was successful.
        doorRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Loop through all documents returned. In this implementation, this is all documents in door collection.
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Map<String, Object> currObj = curr.getData();
                    for (String objType : (List<String>) currObj.get("categories")) {
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

    /**
     * Method to retrieve Item objects from the Firestore database by matching the user specified search string to the stored names of Item documents on Firestore.
     *
     * @param matchString: string that we are looking for.
     * @param callback     callback instance to send notification and data to once the search is complete.
     */
    @Override
    public void getItemsByName(String matchString, DataCallback callback) {
        String[] matchList = matchString.split("\\s+");
        List<Item> resultList = new ArrayList<>();

        Log.d("SEARCH", "Searching for string " + matchString);
        doorRef.whereArrayContainsAny("name", Arrays.asList(matchList)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("FOUND", "Found item matching string");
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
                        door.setFirestoreID(curr.getId());
                        resultList.add(door);
                    }
                    callback.dataListCallback(resultList);
                }
            }
        });
        handleRef.whereArrayContainsAny("name", Arrays.asList(matchList)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("FOUND", "Found item matching string");
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Item handle = curr.toObject(DoorHandle.class);
                    handle.setFirestoreID(curr.getId());
                    resultList.add(handle);
                }
                callback.dataListCallback(resultList);
            }
        });
        callback.dataListCallback(resultList);
    }


    /**
     * Method that retrieves all Items that are from the category selected by the user from the Firestore database.
     *
     * @param categoryName: name of selected category.
     * @param callback      callback instance to send notification and data once the search is complete.
     */
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
//                    StringBuilder mapAsString = new StringBuilder("{");
//                    for (String key : currObj.keySet()) {
//                        mapAsString.append(key + "=" + currObj.get(key) + ", ");
//                    }
//                    mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
//                    Log.d("mapString", mapAsString.toString());
                    for (String objType : (List<String>) currObj.get("categories")) {
                        Item door = null;
                        if (objType.equals(DOOR_TYPES[0])) {
                            door = curr.toObject(MetalDoor.class);
                        } else if (objType.equals(DOOR_TYPES[1])) {
                            door = curr.toObject(GlassDoor.class);
                        } else if (objType.equals(DOOR_TYPES[2])) {
                            door = curr.toObject(WoodenDoor.class);
                        }
                        door.setFirestoreID(curr.getId());
                        resultList.add(door);
                    }
                    callback.dataListCallback(resultList);
                }
            }
        });
        handleRef.whereArrayContainsAny("categories", categoryList).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Item handle = curr.toObject(DoorHandle.class);
                    handle.setFirestoreID(curr.getId());
                    resultList.add(handle);
                }
                callback.dataListCallback(resultList);
            }
        });
    }


    /**
     * Method that retrieves the Item that the user selects (based on the Item id) from the Firestore database.
     *
     * @param id:      Id of item to retrieve from database
     * @param callback callback instance to send notification and data once the search is complete.
     */
    public void getItemByID(int id, DataCallback callback) {
        final Item[] item = new Item[1];
        doorRef.whereEqualTo("id", id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.getDocuments().size() > 0) {
                    DocumentSnapshot docSnap = queryDocumentSnapshots.getDocuments().get(0);
                    Map<String, Object> currObj = queryDocumentSnapshots.getDocuments().get(0).getData();
                    String objType = ((List<String>) currObj.get("categories")).get(0);
//                StringBuilder mapAsString = new StringBuilder("{");
//                for (String key : currObj.keySet()) {
//                    mapAsString.append(key + "=" + currObj.get(key) + ", ");
//                }
//                mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
//                Log.d("mapString", mapAsString.toString());
                    Item i = null;
                    if (objType.equals(DOOR_TYPES[0])) {
                        i = docSnap.toObject(MetalDoor.class);
                    } else if (objType.equals(DOOR_TYPES[1])) {
                        i = docSnap.toObject(GlassDoor.class);
                    } else if (objType.equals(DOOR_TYPES[2])) {
                        i = docSnap.toObject(WoodenDoor.class);
                    }
                    i.setFirestoreID(docSnap.getId());
                    callback.itemCallback(i);
                    item[0] = i;
                }
            }
        });
        handleRef.whereEqualTo("id", id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.getDocuments().size() > 0) {
                    DocumentSnapshot docSnap = queryDocumentSnapshots.getDocuments().get(0);
                    Item i;
                    i = docSnap.toObject(DoorHandle.class);
                    i.setFirestoreID(docSnap.getId());
                    callback.itemCallback(i);
                    item[0] = i;
                }

            }
        });
    }

    /**
     * Method to retrieve a list of items sorted by view count. Calls a callback function to return the result when transaction is completed.
     * @param callback callback instance to send notification and data once the search is complete.
     */
    public void sortItemListByViewCount(DataCallback callback) {
        List<Door> doorList = new ArrayList<>();
        List<Handle> handleList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();

        doorRef.orderBy("viewCount", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                    // Get data of each document to check the category type before deciding which type of Door child object to map to.
                    Map<String, Object> currObj = curr.getData();
                    for (String objType : (List<String>) currObj.get("categories")) {
                        Door door = null;
                        if (objType.equals(DOOR_TYPES[0])) {
                            door = curr.toObject(MetalDoor.class);
                        } else if (objType.equals(DOOR_TYPES[1])) {
                            door = curr.toObject(GlassDoor.class);
                        } else if (objType.equals(DOOR_TYPES[2])) {
                            door = curr.toObject(WoodenDoor.class);
                        }
                        door.setFirestoreID(curr.getId());
                        doorList.add(door);
                    }
                }
                handleRef.orderBy("viewCount", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot curr : queryDocumentSnapshots) {
                            Handle handle = curr.toObject(DoorHandle.class);
                            handle.setFirestoreID(curr.getId());
                            handleList.add(handle);
                        }

                        int doorPointer = 0, handlePointer = 0, itemPointer = 0;
                        while (doorPointer < doorList.size() && handlePointer < handleList.size()) {
                            if (doorList.get(doorPointer).getViewCount() > handleList.get(handlePointer).getViewCount()) {
                                itemList.add(doorList.get(doorPointer));
                                doorPointer++;
                            } else {
                                itemList.add(handleList.get(handlePointer));
                                handlePointer++;
                            }
                            itemPointer++;
                        }

                        while (doorPointer < doorList.size()) {
                            itemList.add(doorList.get(doorPointer));
                            doorPointer++;
                        }
                        while (handlePointer < handleList.size()) {
                            itemList.add(handleList.get(handlePointer));
                            handlePointer++;
                        }
                        callback.dataListCallback(itemList);
                    }
                });


            }
        });


    }


    /**
     * Method to update attribute values of the selected Item (e.g. viewCount when users click on them)
     * TODO: Remove "+1" as the incrementation should be done upon viewer entering detailed view of an item, not here. This is just for testing purposes
     * @param itemChanged The item whose attributes should be updated.
     */
    public void persistData(Item itemChanged) {
        String category = itemChanged.getCategories().get(0);
        Log.d("UPDATE", "Item's view count being updated has id " + itemChanged.getFirestoreID());
        if (Arrays.asList(DOOR_TYPES).contains(category)) {
            doorRef.document(itemChanged.getFirestoreID()).update("viewCount", itemChanged.getViewCount());
        } else if (category.equals(HANDLE_TYPE)) {
            handleRef.document(itemChanged.getFirestoreID()).update("viewCount", itemChanged.getViewCount());
        }
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