package com.example.medicaltermsdictionary.service;

import com.example.medicaltermsdictionary.exception.TermNotFoundException;
import com.example.medicaltermsdictionary.exception.DuplicateFavoriteException;
import com.example.medicaltermsdictionary.model.FavoriteTerm;
import com.example.medicaltermsdictionary.model.MedicalTerm;
import com.example.medicaltermsdictionary.repository.FavoriteTermRepository;
import com.example.medicaltermsdictionary.repository.MedicalTermRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.medicaltermsdictionary.constant.ErrorConstants;

/**
 * Service implementation for managing medical terms, providing functionalities
 * such as retrieving all terms, finding a specific term, searching for terms,
 * finding related terms, and managing favorite terms.
 */
@Service
@Transactional
public class MedicalTermServiceImpl implements MedicalTermService {

    private final MedicalTermRepository medicalTermRepository;
    private final FavoriteTermRepository favoriteTermRepository;

    /**
     * Constructs the MedicalTermServiceImpl with required repositories.
     *
     * @param medicalTermRepository  the repository for MedicalTerm entities
     * @param favoriteTermRepository the repository for managing favorite terms
     */
    public MedicalTermServiceImpl(MedicalTermRepository medicalTermRepository,
            FavoriteTermRepository favoriteTermRepository) {
        this.medicalTermRepository = medicalTermRepository;
        this.favoriteTermRepository = favoriteTermRepository;
    }

    /**
     * Retrieves a list of all available medical terms.
     *
     * @return a list of {@link MedicalTerm} entities representing all terms
     */
    @Override
    public List<MedicalTerm> getAllTerms() {
        return medicalTermRepository.findAll();
    }

    /**
     * Finds a medical term by its name.
     *
     * @param term the name of the medical term to find
     * @return an {@link Optional} containing the found {@link MedicalTerm} if
     *         present, otherwise empty
     */
    @Override
    public Optional<MedicalTerm> getTermByName(String term) {
        return medicalTermRepository.findByTerm(term);
    }

    /**
     * Finds a medical term by its unique ID.
     *
     * @param id the unique identifier of the medical term to find
     * @return an {@link Optional} containing the found {@link MedicalTerm} if
     *         present, otherwise empty
     */
    @Override
    public Optional<MedicalTerm> getTermById(Long id) {
        return medicalTermRepository.findById(id);
    }

    /**
     * Searches for medical terms that contain the specified search string,
     * ignoring case sensitivity.
     *
     * @param term the search string to use for finding matching terms
     * @return a list of {@link MedicalTerm} entities that match the search string
     */
    @Override
    public List<MedicalTerm> searchTerms(String term) {
        return medicalTermRepository.findByTermContainingIgnoreCase(term);
    }

    /**
     * Finds related terms by analyzing the keywords within the specified term's
     * definition.
     * Related terms are those that contain similar keywords in their definitions.
     *
     * @param term the base term to find related terms for
     * @return a list of {@link MedicalTerm} entities that are related to the
     *         specified term
     */
    @Override
    public List<MedicalTerm> getRelatedTerms(String term) {
        return medicalTermRepository.findByTerm(term)
                .map(MedicalTerm::getDefinition)
                .stream()
                .flatMap(def -> List.of(def.split("\\s+")).stream())
                .filter(keyword -> keyword.length() > 3) // Exclude common short words
                .distinct()
                .flatMap(keyword -> medicalTermRepository.findByDefinitionContainingIgnoreCase(keyword).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all favorite medical terms.
     *
     * @return a list of {@link MedicalTerm} entities marked as favorites
     */
    @Override
    public List<MedicalTerm> getFavoriteTerms() {
        return favoriteTermRepository.findAll().stream()
                .map(FavoriteTerm::getMedicalTerm)
                .collect(Collectors.toList());
    }

    /**
     * Adds a medical term to the favorites list by its ID.
     *
     * @param termId the unique identifier of the medical term to add to favorites
     * @throws TermNotFoundException      if the medical term with the specified ID
     *                                    is not found
     * @throws DuplicateFavoriteException if the medical term is already marked as a
     *                                    favorite
     */
    @Override
    public void addToFavorites(Long termId) {
        MedicalTerm medicalTerm = medicalTermRepository.findById(termId)
                .orElseThrow(() -> new TermNotFoundException(ErrorConstants.TERM_NOT_FOUND));
        favoriteTermRepository.findByMedicalTermId(termId).ifPresent(_ -> {
            throw new DuplicateFavoriteException(ErrorConstants.DUPLICATE_FAVORITE);
        });
        favoriteTermRepository.save(new FavoriteTerm(null, medicalTerm));
    }

    /**
     * Removes a medical term from the favorites list by its ID.
     *
     * @param termId the unique identifier of the medical term to remove from
     *               favorites
     * @throws TermNotFoundException if the favorite term with the specified ID is
     *                               not found
     */
    @Override
    public void removeFromFavorites(Long termId) {
        FavoriteTerm favorite = favoriteTermRepository.findByMedicalTermId(termId)
                .orElseThrow(() -> new TermNotFoundException(ErrorConstants.TERM_NOT_FOUND));
        favoriteTermRepository.delete(favorite);
    }
}
