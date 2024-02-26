package com.example.counties.services.impl;

import com.example.counties.dao.CountryDao;
import com.example.counties.dtos.CountryDto;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.ResponseDto;
import com.example.counties.services.CountryService;
import com.example.counties.utils.Continents;
import com.example.counties.utils.Regions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public ResponseDto findAll() {
        Collection<CountryDto> countryDtos=this.countryDao.findAll();
        return new ResponseDto(countryDtos.size(), countryDtos);
    }

    @Override
    public PaginationDto findByPage(int page, int pageSize) {
        return  this.countryDao.findByPage(page, pageSize);
    }

    @Override
    public ResponseDto findByContinent(Continents continent) {
        Collection<CountryDto> countryDtos=this.countryDao.findByContinent(continent);
        return new ResponseDto(countryDtos.size(), countryDtos );
    }

    @Override
    public PaginationDto findByContinentByPage(Continents continent, int page, int pageSize) {
        return this.countryDao.findByContinentByPage(continent, page, pageSize);
    }

    @Override
    public ResponseDto findByRegion(Regions region) {
        Collection<CountryDto> countryDtos=this.countryDao.findByRegion(region);
        return new ResponseDto(countryDtos.size(), countryDtos);
    }

    @Override
    public PaginationDto findByRegionByPage(Regions region, int page, int pageSize) {
        return this.countryDao.findByRegionByPage(region, page, pageSize);
    }

    @Override
    public ResponseDto findByPopulation(Integer population) {
        Collection<CountryDto> countryDtos=this.countryDao.findByPopulation(population);
        return new ResponseDto(countryDtos.size(), countryDtos);
    }


}
