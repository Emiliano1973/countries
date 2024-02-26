package com.example.counties.services.impl;

import com.example.counties.dao.CountryLanguagesDao;
import com.example.counties.dtos.CountryLanguageDto;
import com.example.counties.dtos.ResponseDto;
import com.example.counties.services.CountryLanguageService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CountryLanguageServiceImpl  implements CountryLanguageService {

    private final CountryLanguagesDao countryLanguagesDao;

    public CountryLanguageServiceImpl(CountryLanguagesDao countryLanguagesDao) {
        this.countryLanguagesDao = countryLanguagesDao;
    }

    @Override
    public  ResponseDto getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial) {
        Collection<CountryLanguageDto> countryLanguageDtos= this.countryLanguagesDao.getLanguagesByCountryIdAndIsOfficial(countryCode, isOffcial);
        return new ResponseDto( countryLanguageDtos.size(), countryLanguageDtos);
    }

    @Override
    public ResponseDto getLanguagesByCountryId(String countryCode) {
        Collection<CountryLanguageDto> countryLanguageDtos= this.countryLanguagesDao.getLanguagesByCountryId(countryCode);
        return new ResponseDto( countryLanguageDtos.size(), countryLanguageDtos);
    }
}