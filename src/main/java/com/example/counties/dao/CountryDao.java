package com.example.counties.dao;

import com.example.counties.dtos.CountryDto;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.utils.Continents;
import com.example.counties.utils.Regions;

import java.util.Collection;

public interface CountryDao {



    Collection<CountryDto> findAll();
    PaginationDto findByPage(int page, int pageSize);

    Collection<CountryDto> findByContinent(Continents continent);
    PaginationDto findByContinentByPage(Continents continent, int page, int pageSize);

    Collection<CountryDto> findByRegion(Regions region);

    PaginationDto findByRegionByPage(Regions region, int page, int pageSize);

    Collection<CountryDto> findByPopulation(Integer population);




}
