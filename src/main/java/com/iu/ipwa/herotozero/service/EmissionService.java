package com.iu.ipwa.herotozero.service;

import com.iu.ipwa.herotozero.model.EmissionData;
import com.iu.ipwa.herotozero.repository.EmissionDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionService {

    private final EmissionDataRepository repository;

    public EmissionService(EmissionDataRepository repository) {
        this.repository = repository;
    }

    public List<EmissionData> getAllEmissions() {
        return repository.findAll();
    }

    // NEU: Speichern
    public void save(EmissionData emissionData) {
        repository.save(emissionData);
    }

    // NEU: Suchen nach ID (fürs Bearbeiten)
    public EmissionData findById(long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
    }

    // NEU: Löschen
    public void delete(long id) {
        repository.deleteById(id);
    }
}