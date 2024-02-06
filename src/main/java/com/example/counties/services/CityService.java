package com.example.counties.services;

import com.example.counties.dtos.CityDto;
import com.example.counties.dtos.PaginationDto;

import java.util.Collection;

public interface CityService {


    Collection<CityDto> findByCountryCode(String countryCode);

    PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize);
}
