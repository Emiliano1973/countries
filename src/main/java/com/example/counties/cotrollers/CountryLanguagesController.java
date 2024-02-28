package com.example.counties.cotrollers;

import com.example.counties.dtos.ResponseDto;
import com.example.counties.services.CountryLanguageService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries/languages")
@CacheConfig(cacheNames = "countryLanguage")
public class CountryLanguagesController {
    private final CountryLanguageService countryLanguageService;

    public CountryLanguagesController(final CountryLanguageService countryLanguageService) {
        this.countryLanguageService = countryLanguageService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "countryLanguage", keyGenerator = "customKeyGenerator")
    public ResponseDto getLanguagesByCountryCode(@RequestParam("country_code") final String countryCode){
        return this.countryLanguageService.getLanguagesByCountryId(countryCode);
    }

}
