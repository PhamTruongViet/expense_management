package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private String id;
    private String userId;
    private BigDecimal amount;
    private String transactionType;
    private String category;
    private String subcategory;
    private Date date;

    // Constructors, getters, and setters
    public Transaction() {
    }

    public Transaction(String id, String userId, BigDecimal amount, String transactionType, String category,
            String subcategory, Date date) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.category = category;
        this.subcategory = subcategory;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}