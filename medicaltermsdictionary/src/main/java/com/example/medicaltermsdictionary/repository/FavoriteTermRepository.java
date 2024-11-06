package com.example.medicaltermsdictionary.repository;

import com.example.medicaltermsdictionary.model.FavoriteTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteTermRepository extends JpaRepository<FavoriteTerm, Long> {
    Optional<FavoriteTerm> findByMedicalTermId(Long medicalTermId);
}
