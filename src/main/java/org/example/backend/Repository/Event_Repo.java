package org.example.backend.Repository;

import org.example.backend.Entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Event_Repo extends JpaRepository<Events, Integer> {
}
