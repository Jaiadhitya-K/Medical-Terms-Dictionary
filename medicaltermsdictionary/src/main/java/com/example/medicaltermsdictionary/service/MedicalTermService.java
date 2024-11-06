package com.example.medicaltermsdictionary.service;

import com.example.medicaltermsdictionary.model.MedicalTerm;
import java.util.List;
//import java.util.Optional;

public interface MedicalTermService {
    List<MedicalTerm> getAllTerms();
}