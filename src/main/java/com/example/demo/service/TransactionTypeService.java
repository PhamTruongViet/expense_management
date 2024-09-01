package com.example.demo.service;

import com.example.demo.model.TransactionType;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionTypeService {

    private static final String COLLECTION_NAME = "transactiontypes";

    public String createTransactionType(TransactionType transactionType)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COLLECTION_NAME).document();
        transactionType.setId(docRef.getId());
        ApiFuture<WriteResult> collectionsApiFuture = docRef.set(transactionType);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public TransactionType getTransactionType(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(TransactionType.class);
        }
        return null;
    }

    public List<TransactionType> getAllTransactionTypes() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<TransactionType> transactionTypes = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            transactionTypes.add(document.toObject(TransactionType.class));
        }
        return transactionTypes;
    }

    public String updateTransactionType(TransactionType transactionType)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME)
                .document(transactionType.getId()).set(transactionType);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteTransactionType(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
        writeResult.get(); // Đảm bảo thao tác xóa hoàn tất
        return "Đã xóa TransactionType có ID " + id;
    }

    public String addCategory(String transactionTypeId, String category)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COLLECTION_NAME).document(transactionTypeId);

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            TransactionType transactionType = document.toObject(TransactionType.class);
            transactionType.addCategory(category);

            ApiFuture<WriteResult> updateFuture = docRef.set(transactionType);
            return updateFuture.get().getUpdateTime().toString();
        }

        return "TransactionType không tồn tại";
    }

    public String addSubcategory(String transactionTypeId, String category, String subcategory)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COLLECTION_NAME).document(transactionTypeId);

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            TransactionType transactionType = document.toObject(TransactionType.class);
            transactionType.addSubcategory(category, subcategory);

            ApiFuture<WriteResult> updateFuture = docRef.set(transactionType);
            return updateFuture.get().getUpdateTime().toString();
        }

        return "TransactionType không tồn tại";
    }
}