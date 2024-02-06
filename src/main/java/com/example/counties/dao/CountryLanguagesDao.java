package com.example.counties.dao;

import com.example.counties.dtos.CountryLanguageDto;

import java.util.Collection;

public interface CountryLanguagesDao {

    Collection<CountryLanguageDto> getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial);

    Collection<CountryLanguageDto> getLanguagesByCountryId(String countryCode);

}
