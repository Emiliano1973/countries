package com.example.counties.cotrollers;

import com.example.counties.dtos.CityDto;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.ResponseDto;
import com.example.counties.entities.City;
import com.example.counties.services.CityService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/cities")
@CacheConfig(cacheNames = "city")
public class CityController {

    private final CityService cityService;

    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "city",  keyGenerator = "customKeyGenerator")
    public ResponseDto getAll(@RequestParam("country_code") final String countryCode){
        Collection<CityDto> cities= this.cityService.findByCountryCode(countryCode);
        return new ResponseDto(cities.size(), cities);
    }

    @GetMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "city",  keyGenerator = "customKeyGenerator")
    public PaginationDto getAllByPage(@RequestParam("country_code") final String countryCode, @RequestParam("page") final int page,
                                      @RequestParam("pageSize")  int pageSize){
        return this.cityService.findByCountryCodeByPage(countryCode, page, pageSize);
    }
}
