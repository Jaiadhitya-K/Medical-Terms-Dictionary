package com.example.medicaltermsdictionary.repository;

import com.example.medicaltermsdictionary.model.MedicalTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link MedicalTerm} entities.
 * Provides methods for common CRUD operations as well as custom queries
 * for retrieving terms based on name and content.
 */
@Repository
public interface MedicalTermRepository extends JpaRepository<MedicalTerm, Long> {

    /**
     * Finds a medical term by its exact name.
     *
     * @param term the exact name of the medical term
     * @return an {@link Optional} containing the found {@link MedicalTerm} if
     *         present, otherwise empty
     */
    Optional<MedicalTerm> findByTerm(String term);

    /**
     * Searches for medical terms that contain the specified name, ignoring case
     * sensitivity.
     *
     * @param term the substring to search for within medical terms
     * @return a list of {@link MedicalTerm} entities that match the specified term
     *         substring
     */
    List<MedicalTerm> findByTermContainingIgnoreCase(String term);

    /**
     * Searches for medical terms whose definitions contain the specified keyword,
     * ignoring case sensitivity.
     *
     * @param keyword the keyword to search for within medical term definitions
     * @return a list of {@link MedicalTerm} entities that contain the specified
     *         keyword in their definitions
     */
    List<MedicalTerm> findByDefinitionContainingIgnoreCase(String keyword);
}
