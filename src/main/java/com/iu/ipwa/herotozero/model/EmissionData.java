package com.iu.ipwa.herotozero.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "emission_data")
public class EmissionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(name = "emission_year")
    private Integer year;

    @Column(name = "emission_value")
    private Double value;
}