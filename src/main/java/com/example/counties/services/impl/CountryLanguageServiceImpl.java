package com.example.counties.services.impl;

import com.example.counties.dao.CountryLanguagesDao;
import com.example.counties.dtos.ResponseDto;
import com.example.counties.services.CountryLanguageService;
import org.springframework.stereotype.Service;

@Service
public class CountryLanguageServiceImpl  implements CountryLanguageService {

    private final CountryLanguagesDao countryLanguagesDao;

    public CountryLanguageServiceImpl(CountryLanguagesDao countryLanguagesDao) {
        this.countryLanguagesDao = countryLanguagesDao;
    }

    @Override
    public  ResponseDto getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial) {
        return new ResponseDto( this.countryLanguagesDao.getLanguagesByCountryIdAndIsOfficial(countryCode, isOffcial));
    }

    @Override
    public ResponseDto getLanguagesByCountryId(String countryCode) {
        return new ResponseDto(this.countryLanguagesDao.getLanguagesByCountryId((countryCode)));
    }
}
