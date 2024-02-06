package com.example.counties.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "City")
@Data
@Table(name = "City", schema = "world")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "District")
    private String district;

    @Column(name = "Population")
    private Integer population;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode", referencedColumnName = "Code")
    private Country country;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "capital")
    private Country countryCapital;

}
