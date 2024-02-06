package com.example.counties.entities;

import com.example.counties.utils.BooleanConverter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "countrylanguage", schema = "world")
public class CountryLanguage {

    @EmbeddedId
    private CountryLanguagePk countryLanguagePk;

    @Column(name = "IsOfficial")
    @Convert(converter = BooleanConverter.class)
    private Boolean officialLanguage;

    @Column(name = "Percentage")
    private Double percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode", referencedColumnName = "Code", updatable = false, insertable = false)
    private Country country;



}
