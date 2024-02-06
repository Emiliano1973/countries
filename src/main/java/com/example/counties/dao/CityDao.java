package com.example.counties.dao;

import com.example.counties.dtos.CityDto;
import com.example.counties.dtos.PaginationDto;

import java.util.Collection;

public interface CityDao {

    Collection<CityDto> findByCountryCode(String countryCode);

    PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize);

}
