package com.example.counties.services;

import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.ResponseDto;
import com.example.counties.utils.Continents;
import com.example.counties.utils.Regions;

public interface CountryService {
    ResponseDto findAll();
    PaginationDto findByPage(int page, int pageSize);

    ResponseDto findByContinent(Continents continent);
    PaginationDto findByContinentByPage(Continents continent, int page, int pageSize);

    ResponseDto findByRegion(Regions region);

    PaginationDto findByRegionByPage(Regions region, int page, int pageSize);


    ResponseDto findByPopulation(Integer population);

    ResponseDto findByEndip(String isIndepYear);
}
