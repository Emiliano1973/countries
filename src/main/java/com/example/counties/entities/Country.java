package com.example.counties.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Country")
@Data
@Table(name = "Country", schema = "world")
public class Country {

    @Id
    @Column(name = "Code")
    private String countryCode;

    @Column(name = "Name")
    private String name;

    @Column(name = "Continent")
    private String continent;
    @Column(name = "Region")
    private String region;

    @Column(name = "SurfaceArea")
    private  Double surfaceArea;

    @Column(name = "IndepYear")
    private Integer indepYear;

    @Column(name = "Population")
    private Integer population;

    @Column(name = "LifeExpectancy")
    private  Double lifeExpectancy;

    @Column(name = "GNP")
    private  Double gnp;
    @Column(name = "GNPOld")
    private  Double gnpOld;

    @Column(name = "LocalName")
    private String localName ;

    @Column(name = "GovernmentForm")
    private String governmentForm ;
    @Column(name = "HeadOfState")
    private String headOfState ;
            //`country`.`Capital`,

    @Column(name = "Code2")
    private String countryCode2;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<City> cities=new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capital", referencedColumnName = "id")
    private City capital;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<CountryLanguage> languages=new HashSet<>();

}
