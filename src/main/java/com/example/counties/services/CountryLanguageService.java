package com.example.counties.services;

import com.example.counties.dtos.ResponseDto;

public interface CountryLanguageService {
    ResponseDto getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial);

    ResponseDto getLanguagesByCountryId(String countryCode);

}