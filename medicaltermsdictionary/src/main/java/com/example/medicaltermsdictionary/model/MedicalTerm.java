package com.example.medicaltermsdictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "medical_terms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String term;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String definition;
}
