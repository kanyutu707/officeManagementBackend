package org.example.backend.Repository;

import org.example.backend.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Company_Repo extends JpaRepository<Company, Integer> {
}
