package com.example.medicaltermsdictionary.controller;

import com.example.medicaltermsdictionary.model.MedicalTerm;
import com.example.medicaltermsdictionary.service.MedicalTermService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing medical terms and their related functionalities,
 * such as retrieving terms, searching, and managing favorites.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/medicalterms")
public class MedicalTermController {

    private final MedicalTermService medicalTermService;

    /**
     * Constructs the MedicalTermController with the required service dependency.
     *
     * @param medicalTermService the service layer handling medical term operations
     */
    public MedicalTermController(MedicalTermService medicalTermService) {
        this.medicalTermService = medicalTermService;
    }

    /**
     * Retrieves all available medical terms.
     *
     * @return ResponseEntity containing a list of all medical terms
     */
    @GetMapping
    public ResponseEntity<List<MedicalTerm>> getAllTerms() {
        List<MedicalTerm> terms = medicalTermService.getAllTerms();
        return ResponseEntity.ok(terms);
    }

    /**
     * Retrieves a medical term by its name.
     *
     * @param term the name of the medical term to retrieve
     * @return ResponseEntity containing the medical term, if found
     */
    @GetMapping("/name/{term}")
    public ResponseEntity<MedicalTerm> getTermByName(@PathVariable String term) {
        Optional<MedicalTerm> medicalTerm = medicalTermService.getTermByName(term);
        return medicalTerm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves a medical term by its unique ID.
     *
     * @param id the ID of the medical term
     * @return ResponseEntity containing the medical term, if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedicalTerm> getTermById(@PathVariable Long id) {
        Optional<MedicalTerm> medicalTerm = medicalTermService.getTermById(id);
        return medicalTerm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Searches for medical terms containing the specified query string.
     *
     * @param query the search term
     * @return ResponseEntity containing a list of medical terms matching the query
     */
    @GetMapping("/search")
    public ResponseEntity<List<MedicalTerm>> searchTerms(@RequestParam String query) {
        List<MedicalTerm> terms = medicalTermService.searchTerms(query);
        return ResponseEntity.ok(terms);
    }

    /**
     * Retrieves related terms based on the specified medical term.
     *
     * @param term the base medical term to find related terms for
     * @return ResponseEntity containing a list of related medical terms
     */
    @GetMapping("/{term}/related")
    public ResponseEntity<List<MedicalTerm>> getRelatedTerms(@PathVariable String term) {
        List<MedicalTerm> relatedTerms = medicalTermService.getRelatedTerms(term);
        return ResponseEntity.ok(relatedTerms);
    }

    /**
     * Retrieves all favorite medical terms.
     *
     * @return ResponseEntity containing a list of favorite medical terms
     */
    @GetMapping("/favorites")
    public ResponseEntity<List<MedicalTerm>> getFavoriteTerms() {
        List<MedicalTerm> favoriteTerms = medicalTermService.getFavoriteTerms();
        return ResponseEntity.ok(favoriteTerms);
    }

    /**
     * Adds a medical term to the favorites list.
     *
     * @param id the ID of the medical term to be added to favorites
     * @return ResponseEntity with HTTP status indicating success or failure
     */
    @PostMapping("/{id}/favorites")
    public ResponseEntity<Void> addFavorite(@PathVariable Long id) {
        medicalTermService.addToFavorites(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Removes a medical term from the favorites list.
     *
     * @param id the ID of the medical term to be removed from favorites
     * @return ResponseEntity with HTTP status indicating success or failure
     */
    @DeleteMapping("/{id}/favorites")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long id) {
        medicalTermService.removeFromFavorites(id);
        return ResponseEntity.noContent().build();
    }
}
