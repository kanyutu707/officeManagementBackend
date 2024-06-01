package org.example.backend.Repository;

import org.example.backend.Entity.Financials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Financial_Repo extends JpaRepository<Financials, Integer> {
}
