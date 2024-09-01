package com.example.demo.controller;

import com.example.demo.service.AuthenticationService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> credentials) {
        try {
            String uid = authenticationService.registerUser(credentials.get("email"), credentials.get("password"));
            return ResponseEntity.ok("Đăng ký thành công. UID: " + uid);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.badRequest().body("Đăng ký thất bại: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> credentials) {
        try {
            String uid = authenticationService.loginUser(credentials.get("email"), credentials.get("password"));
            return ResponseEntity.ok("Đăng nhập thành công. UID: " + uid);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.badRequest().body("Đăng nhập thất bại: " + e.getMessage());
        }
    }
}