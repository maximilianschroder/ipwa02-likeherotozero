package com.iu.ipwa.herotozero.repository;

import com.iu.ipwa.herotozero.model.EmissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionDataRepository extends JpaRepository<EmissionData, Long> {
    // Leer!
    // Durch "extends JpaRepository" haben wir automatisch Methoden wie:
    // .findAll(), .save(), .deleteById(), usw. geerbt.
}