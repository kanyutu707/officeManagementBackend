package org.example.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tasks {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Title;
    private String Description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="assignedTo", referencedColumnName = "id")
    private User user;
    private Timestamp created;
    private Timestamp deadline;
}
