package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.TransactionType;
import com.example.demo.model.Transaction;
import com.example.demo.service.UserService;
import com.example.demo.service.TransactionTypeService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    @PutMapping
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/transactiontypes")
    public String createTransactionType(@RequestBody TransactionType transactionType)
            throws ExecutionException, InterruptedException {
        return transactionTypeService.createTransactionType(transactionType);
    }

    @GetMapping("/transactiontypes/{id}")
    public TransactionType getTransactionType(@PathVariable String id) throws ExecutionException, InterruptedException {
        return transactionTypeService.getTransactionType(id);
    }

    @GetMapping("/transactiontypes")
    public List<TransactionType> getAllTransactionTypes() throws ExecutionException, InterruptedException {
        return transactionTypeService.getAllTransactionTypes();
    }

    @PutMapping("/transactiontypes")
    public String updateTransactionType(@RequestBody TransactionType transactionType)
            throws ExecutionException, InterruptedException {
        return transactionTypeService.updateTransactionType(transactionType);
    }

    @PostMapping("/transactions")
    public String createTransaction(@RequestBody Transaction transaction)
            throws ExecutionException, InterruptedException {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable String id) throws ExecutionException, InterruptedException {
        return transactionService.getTransaction(id);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() throws ExecutionException, InterruptedException {
        return transactionService.getAllTransactions();
    }

    @PutMapping("/transactions")
    public String updateTransaction(@RequestBody Transaction transaction)
            throws ExecutionException, InterruptedException {
        return transactionService.updateTransaction(transaction);
    }

    @DeleteMapping("/transactions/{id}")
    public String deleteTransaction(@PathVariable String id) {
        return transactionService.deleteTransaction(id);
    }

    @PostMapping("/transactiontypes/{id}/categories")
    public String addCategory(@PathVariable String id, @RequestBody String category)
            throws ExecutionException, InterruptedException {
        return transactionTypeService.addCategory(id, category);
    }

    @PostMapping("/transactiontypes/{id}/categories/{category}/subcategories")
    public String addSubcategory(@PathVariable String id, @PathVariable String category,
            @RequestBody String subcategory) throws ExecutionException, InterruptedException {
        return transactionTypeService.addSubcategory(id, category, subcategory);
    }

    @GetMapping("/transactions/type/{transactionType}")
    public List<Transaction> getTransactionsByType(@PathVariable String transactionType)
            throws ExecutionException, InterruptedException {
        return transactionService.getTransactionsByType(transactionType);
    }

    @GetMapping("/transactions/category/{category}")
    public List<Transaction> getTransactionsByCategory(@PathVariable String category)
            throws ExecutionException, InterruptedException {
        return transactionService.getTransactionsByCategory(category);
    }

    @GetMapping("/transactions/subcategory/{subcategory}")
    public List<Transaction> getTransactionsBySubcategory(@PathVariable String subcategory)
            throws ExecutionException, InterruptedException {
        return transactionService.getTransactionsBySubcategory(subcategory);
    }
}