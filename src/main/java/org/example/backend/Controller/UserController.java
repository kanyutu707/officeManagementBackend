package org.example.backend.Controller;

import org.example.backend.Entity.User;
import org.example.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/smartEmployer/user")
public class UserController {
    private UserService service;

    @Autowired
    public void Service(UserService service) {
        this.service = service;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<User>> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return service.getUserByEmail(email);
    }
}
