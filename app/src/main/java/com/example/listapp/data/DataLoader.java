package com.example.listapp.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.listapp.model.Door;
import com.example.listapp.model.DoorHandle;
import com.example.listapp.model.GlassDoor;
import com.example.listapp.model.Handle;
import com.example.listapp.model.Item;
import com.example.listapp.model.MetalDoor;
import com.example.listapp.model.WoodenDoor;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
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

    private String[] DOOR_TYPES = {"metallic", "glass", "wooden"};
    private String HANDLE_TYPE = "handle";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference doorRef = db.collection("door");
    private CollectionReference handleRef = db.collection("handle");

    private static DataLoader instance;

    private DataLoader() {}

    public static DataLoader getDataLoader() {
        if (instance == null) {
            instance = new DataLoader();
        }

        return instance;
    }


    /**
     * Method to retrieve Item objects from the Firestore database by matching the user specified search string to the stored names of Item documents on Firestore.
     *
     * @param matchString: string that we are looking for.
     * @param callback     callback instance to send notification and data to once the search is complete.
     */
    @Override
    public void getItemsByName(String matchString, IDataCallback callback) {
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
                        door.setFirestoreID(curr.getId());
                        resultList.add(door);
                    }
                }

                handleRef.whereArrayContainsAny("name", Arrays.asList(matchList)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
        });

    }


    /**
     * Method that retrieves all Items that are from the category selected by the user from the Firestore database.
     *
     * @param categoryName: name of selected category.
     * @param callback      callback instance to send notification and data once the search is complete.
     */
    @Override
    public void getItemsByCriteria(String categoryName, IDataCallback callback) {
        List<String> categoryList = new ArrayList<>();
        categoryList.add(categoryName);
        List<Item> resultList = new ArrayList<>();

        doorRef.whereArrayContainsAny("categories", categoryList).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                        door.setFirestoreID(curr.getId());
                        resultList.add(door);
                    }
                }
                callback.dataListCallback(resultList);
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
    public void getItemByID(int id, IDataCallback callback) {
        final Item[] item = new Item[1];
        doorRef.whereEqualTo("id", id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.getDocuments().size() > 0) {
                    DocumentSnapshot docSnap = queryDocumentSnapshots.getDocuments().get(0);
                    Map<String, Object> currObj = queryDocumentSnapshots.getDocuments().get(0).getData();
                    String objType = ((List<String>) currObj.get("categories")).get(0);
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
     * Method that retrieves all Items whose IDs are supplied as a parameter.
     *
     * @param idList: list of ids of Items to retrieve from database
     * @param callback      callback instance to send notification and data once the search is complete.
     */
    public void getItemsByID(List<Integer> idList, IDataCallback callback) {
        List<Item> resultList = new ArrayList<>();

        doorRef.whereIn("id", idList).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                        door.setFirestoreID(curr.getId());
                        resultList.add(door);
                    }
                }
                callback.dataListCallback(resultList);
            }
        });
        handleRef.whereIn("id", idList).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
     * Method to retrieve a list of items sorted by view count. Calls a callback function to return the result when transaction is completed.
     * @param callback callback instance to send notification and data once the search is complete.
     */
    public void sortItemListByViewCount(IDataCallback callback) {
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
     * @param itemChanged The item whose attributes should be updated.
     */
    public void persistData(Item itemChanged) {
        String category = itemChanged.getCategories().get(0);
        if (Arrays.asList(DOOR_TYPES).contains(category)) {
            doorRef.document(itemChanged.getFirestoreID()).update("viewCount", itemChanged.getViewCount());
        } else if (category.equals(HANDLE_TYPE)) {
            handleRef.document(itemChanged.getFirestoreID()).update("viewCount", itemChanged.getViewCount());
        }
    }

}