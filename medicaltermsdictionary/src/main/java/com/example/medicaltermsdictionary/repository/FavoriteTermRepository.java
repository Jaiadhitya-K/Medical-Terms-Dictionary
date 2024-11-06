package com.example.medicaltermsdictionary.repository;

import com.example.medicaltermsdictionary.model.FavoriteTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link FavoriteTerm} entities.
 * Provides methods for CRUD operations and custom queries to manage favorite
 * terms.
 */
@Repository
public interface FavoriteTermRepository extends JpaRepository<FavoriteTerm, Long> {

    /**
     * Finds a favorite term by the associated medical term's ID.
     *
     * @param medicalTermId the unique identifier of the medical term associated
     *                      with the favorite
     * @return an {@link Optional} containing the found {@link FavoriteTerm} if
     *         present, otherwise empty
     */
    Optional<FavoriteTerm> findByMedicalTermId(Long medicalTermId);
}
