package com.example.firebase.springbootfirebasedemo.service;

import com.example.firebase.springbootfirebasedemo.entity.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String saveUser(Users user) {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)
                .document(user.getUsername())
                .set(user);

        try {
            return collectionApiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Users getUserDetails(String username) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME)
                .document(username);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Users user = null;
        if(document.exists()) {
            user = document.toObject(Users.class);
            return user;
        } else {
            return null;
        }
    }

    public String updateUser(Users user) {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)
                .document(user.getUsername())
                .set(user);

        try {
            return collectionApiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteUser(String username) {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)
                .document(username)
                .delete();

        return "USER "+username+" IS DELETED";
    }

    public List<Users> getUsers() throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME)
                .listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Users> usersList = new ArrayList<>();
        while(iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();

            DocumentSnapshot document = future.get();



            Users user = document.toObject(Users.class);
            usersList.add(user);


        }

        return usersList;
    }
}
