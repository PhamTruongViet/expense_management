package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionService {

    private static final String COLLECTION_NAME = "transactions";

    public String createTransaction(Transaction transaction) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COLLECTION_NAME).document();
        transaction.setId(docRef.getId());
        ApiFuture<WriteResult> collectionsApiFuture = docRef.set(transaction);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Transaction getTransaction(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Transaction.class);
        }
        return null;
    }

    public List<Transaction> getAllTransactions() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Transaction> transactions = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            transactions.add(document.toObject(Transaction.class));
        }
        return transactions;
    }

    public String updateTransaction(Transaction transaction) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME)
                .document(transaction.getId()).set(transaction);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteTransaction(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
        return "Đã xóa Transaction có ID " + id;
    }

    public List<Transaction> getTransactionsByType(String transactionType)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME)
                .whereEqualTo("transactionType", transactionType).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Transaction> transactions = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            transactions.add(document.toObject(Transaction.class));
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByCategory(String category)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME)
                .whereEqualTo("category", category).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Transaction> transactions = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            transactions.add(document.toObject(Transaction.class));
        }
        return transactions;
    }

    public List<Transaction> getTransactionsBySubcategory(String subcategory)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME)
                .whereEqualTo("subcategory", subcategory).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Transaction> transactions = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            transactions.add(document.toObject(Transaction.class));
        }
        return transactions;
    }
}