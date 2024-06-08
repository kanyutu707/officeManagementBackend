package org.example.backend.Repository;

import org.example.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User_Repo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
