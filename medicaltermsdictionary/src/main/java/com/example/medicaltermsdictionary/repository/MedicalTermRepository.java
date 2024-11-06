package com.example.medicaltermsdictionary.repository;

import com.example.medicaltermsdictionary.model.MedicalTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalTermRepository extends JpaRepository<MedicalTerm, Long> {

    Optional<MedicalTerm> findByTerm(String term);

    List<MedicalTerm> findByTermContainingIgnoreCase(String term);

    List<MedicalTerm> findByDefinitionContainingIgnoreCase(String keyword);
}
