package com.example.medicaltermsdictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

/**
 * Entity representing a favorite medical term. Links to a single
 * {@link MedicalTerm}
 * that is marked as a favorite by the user. Stored in the "favorite_terms"
 * table.
 */
@Entity
@Table(name = "favorite_terms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteTerm {

    /**
     * The unique identifier for each favorite term entry.
     * Generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The medical term that is marked as a favorite. Each favorite is associated
     * with
     * one unique medical term.
     */
    @OneToOne
    @JoinColumn(name = "medical_term_id", unique = true, nullable = false)
    private MedicalTerm medicalTerm;
}
