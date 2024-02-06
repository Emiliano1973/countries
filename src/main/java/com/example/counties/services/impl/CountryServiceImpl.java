package com.example.counties.services.impl;

import com.example.counties.dao.CountryDao;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.ResponseDto;
import com.example.counties.services.CountryService;
import com.example.counties.utils.Continents;
import com.example.counties.utils.Regions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public ResponseDto findAll() {
        return new ResponseDto(this.countryDao.findAll());
    }

    @Override
    public PaginationDto findByPage(int page, int pageSize) {
        return  this.countryDao.findByPage(page, pageSize);
    }

    @Override
    public ResponseDto findByContinent(Continents continent) {
        return new ResponseDto( this.countryDao.findByContinent(continent));
    }

    @Override
    public PaginationDto findByContinentByPage(Continents continent, int page, int pageSize) {
        return this.countryDao.findByContinentByPage(continent, page, pageSize);
    }

    @Override
    public ResponseDto findByRegion(Regions region) {
        return new ResponseDto(this.countryDao.findByRegion(region));
    }

    @Override
    public PaginationDto findByRegionByPage(Regions region, int page, int pageSize) {
        return this.countryDao.findByRegionByPage(region, page, pageSize);
    }
}
