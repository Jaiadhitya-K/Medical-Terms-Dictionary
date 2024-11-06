package com.example.medicaltermsdictionary.service;

import com.example.medicaltermsdictionary.model.MedicalTerm;
import java.util.List;
import java.util.Optional;

public interface MedicalTermService {
    List<MedicalTerm> getAllTerms();

    Optional<MedicalTerm> getTermByName(String term);

    Optional<MedicalTerm> getTermById(Long id);

    List<MedicalTerm> searchTerms(String term);

    List<MedicalTerm> getRelatedTerms(String term);

    // List<MedicalTerm> getFavoriteTerms();

    // void addToFavorites(Long termId);

    // void removeFromFavorites(Long termId);

}