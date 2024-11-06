package com.example.medicaltermsdictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

/**
 * Entity representing a medical term, including its name and definition.
 * Stored in the "medical_terms" table within the database.
 */
@Entity
@Table(name = "medical_terms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalTerm {

    /**
     * The unique identifier for each medical term entry.
     * Generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the medical term, which must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String term;

    /**
     * The definition of the medical term. Stored as text and cannot be null.
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String definition;
}
