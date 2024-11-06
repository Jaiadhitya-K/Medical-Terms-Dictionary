package com.example.medicaltermsdictionary.controller;

import com.example.medicaltermsdictionary.model.MedicalTerm;
import com.example.medicaltermsdictionary.service.MedicalTermService;

import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/medicalterms")
public class MedicalTermController {

    private final MedicalTermService medicalTermService;

    public MedicalTermController(MedicalTermService medicalTermService) {
        this.medicalTermService = medicalTermService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalTerm>> getAllTerms() {
        List<MedicalTerm> terms = medicalTermService.getAllTerms();
        return ResponseEntity.ok(terms);
    }

    @GetMapping("/name/{term}")
    public ResponseEntity<MedicalTerm> getTermByName(@PathVariable String term) {
        Optional<MedicalTerm> medicalTerm = medicalTermService.getTermByName(term);
        return medicalTerm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalTerm> getTermById(@PathVariable Long id) {
        Optional<MedicalTerm> medicalTerm = medicalTermService.getTermById(id);
        return medicalTerm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicalTerm>> searchTerms(@RequestParam String query) {
        List<MedicalTerm> terms = medicalTermService.searchTerms(query);
        return ResponseEntity.ok(terms);
    }
}