package com.example.medicaltermsdictionary.service;

import com.example.medicaltermsdictionary.model.MedicalTerm;
import com.example.medicaltermsdictionary.repository.MedicalTermRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;

@Service
@Transactional
public class MedicalTermServiceImpl implements MedicalTermService {

    private final MedicalTermRepository medicalTermRepository;

    public MedicalTermServiceImpl(MedicalTermRepository medicalTermRepository) {
        this.medicalTermRepository = medicalTermRepository;
    }

    @Override
    public List<MedicalTerm> getAllTerms() {
        return medicalTermRepository.findAll();
    }
}