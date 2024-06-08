package org.example.backend.Service;

import org.example.backend.Entity.User;
import org.example.backend.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private User_Repo repository;

    @Autowired
    public void Repository(User_Repo repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = repository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    public ResponseEntity<User> getUserByEmail(String email){
        Optional<User> userOptional = repository.findByEmail(email);
        return userOptional.map(users->new ResponseEntity<>(users, HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

