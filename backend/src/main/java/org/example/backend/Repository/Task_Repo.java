package org.example.backend.Repository;

import org.example.backend.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Task_Repo extends JpaRepository<Tasks, Integer> {
}
