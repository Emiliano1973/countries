package com.example.counties.services.impl;

import com.example.counties.dao.CityDao;
import com.example.counties.dtos.CityDto;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.services.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
@Service
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Collection<CityDto> findByCountryCode(String countryCode) {
        return this.cityDao.findByCountryCode(countryCode);
    }

    @Override
    public PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize) {
        return this.cityDao.findByCountryCodeByPage(countryCode, page, pageSize);
    }
}
