package com.example.medicaltermsdictionary.service;

import com.example.medicaltermsdictionary.model.FavoriteTerm;
import com.example.medicaltermsdictionary.model.MedicalTerm;
import com.example.medicaltermsdictionary.repository.MedicalTermRepository;
import com.example.medicaltermsdictionary.repository.FavoriteTermRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicalTermServiceImpl implements MedicalTermService {

    private final MedicalTermRepository medicalTermRepository;
    private final FavoriteTermRepository favoriteTermRepository;

    public MedicalTermServiceImpl(MedicalTermRepository medicalTermRepository,
            FavoriteTermRepository favoriteTermRepository) {
        this.medicalTermRepository = medicalTermRepository;
        this.favoriteTermRepository = favoriteTermRepository;
    }

    @Override
    public List<MedicalTerm> getAllTerms() {
        return medicalTermRepository.findAll();
    }

    @Override
    public Optional<MedicalTerm> getTermByName(String term) {
        return medicalTermRepository.findByTerm(term);
    }

    @Override
    public Optional<MedicalTerm> getTermById(Long id) {
        return medicalTermRepository.findById(id);
    }

    @Override
    public List<MedicalTerm> searchTerms(String term) {
        return medicalTermRepository.findByTermContainingIgnoreCase(term);
    }

    @Override
    public List<MedicalTerm> getRelatedTerms(String term) {
        return medicalTermRepository.findByTerm(term)
                .map(MedicalTerm::getDefinition)
                .stream()
                .flatMap(def -> List.of(def.split("\\s+")).stream())
                .filter(keyword -> keyword.length() > 3)
                .distinct()
                .flatMap(keyword -> medicalTermRepository.findByDefinitionContainingIgnoreCase(keyword).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalTerm> getFavoriteTerms() {
        return favoriteTermRepository.findAll().stream()
                .map(FavoriteTerm::getMedicalTerm)
                .collect(Collectors.toList());
    }

    @Override
    public void addToFavorites(Long termId) {
        medicalTermRepository.findById(termId).ifPresent(medicalTerm -> {
            favoriteTermRepository.findByMedicalTermId(termId).ifPresentOrElse(
                    _ -> {
                    },
                    () -> favoriteTermRepository.save(new FavoriteTerm(null, medicalTerm)));
        });
    }

    @Override
    public void removeFromFavorites(Long termId) {
        favoriteTermRepository.findByMedicalTermId(termId).ifPresent(favoriteTermRepository::delete);
    }
}