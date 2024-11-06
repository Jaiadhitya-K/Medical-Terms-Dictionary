package com.example.medicaltermsdictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "favorite_terms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "medical_term_id", unique = true, nullable = false)
    private MedicalTerm medicalTerm;
}
