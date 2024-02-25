package com.example.counties.dao.impl;

import com.example.counties.dao.CountryDao;
import com.example.counties.dtos.CountryDto;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.PaginatorDtoBuilder;
import com.example.counties.entities.City;
import com.example.counties.entities.Country;
import com.example.counties.utils.Continents;
import com.example.counties.utils.Regions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.counties.utils.CityFieldNames.CITY_NAME;
import static com.example.counties.utils.CountryFieldNames.*;

@Repository
public class CountryDaoImpl implements CountryDao {
    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CountryDto> findAll() {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(CAPITAL, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByPage(final int page, final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countAll(cb);
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0).setPageSize(pageSize).setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(CAPITAL, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages).setPageSize(pageSize).setTotalElements(counts).setElements(countryDtos).createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByContinent(final Continents continent) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(CAPITAL, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(cb.upper(countryRoot.get(CONTINENT)),
                        continent.getContinentName().toUpperCase()));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByContinentByPage(final Continents continent, final int page,
                                               final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countByFieldName(cb,CONTINENT, continent.getContinentName());
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0).setPageSize(pageSize)
                    .setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(CAPITAL, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(CONTINENT), continent.getContinentName()));
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages).setPageSize(pageSize).setTotalElements(counts).setElements(countryDtos).createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByRegion(final Regions region) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(CAPITAL, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(REGION), region.getRegionName()));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();


    }

    @Override
    public PaginationDto findByRegionByPage(final Regions region, final int page, final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countByFieldName(cb, REGION, region.getRegionName());
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0).setPageSize(pageSize).setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(CAPITAL, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(REGION), region.getRegionName()));
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages)
                .setPageSize(pageSize).setTotalElements(counts).setElements(countryDtos)
                .createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByPopulation(Integer population) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(CAPITAL, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.lessThanOrEqualTo(countryRoot.get(POPULATION), population));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    private int countAll(CriteriaBuilder cb ) {
        CriteriaQuery<Long> countryDtoCriteriaQuery = cb.createQuery(Long.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        countryDtoCriteriaQuery.select(cb.count(countryRoot.get(COUNTRY_CODE)));
        return this.em.createQuery(countryDtoCriteriaQuery).getSingleResult().intValue();
    }

    private int countByFieldName(CriteriaBuilder cb ,final String fieldName, final String value) {
        CriteriaQuery<Long> countryDtoCriteriaQuery = cb.createQuery(Long.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        countryDtoCriteriaQuery.select(cb.count(countryRoot.get(COUNTRY_CODE)))
                .where(cb.equal(countryRoot.get(fieldName), value));
        return this.em.createQuery(countryDtoCriteriaQuery).getSingleResult().intValue();
    }


    private void setCountryField(CriteriaBuilder cb ,CriteriaQuery<CountryDto> countryDtoCriteriaQuery,
                                 Root<Country> countryRoot, Join<Country, City> countryCityJoin){
        countryDtoCriteriaQuery.multiselect
                        (countryRoot.get(COUNTRY_CODE),
                                countryRoot.get(COUNTRY_NAME),
                                countryRoot.get(CONTINENT),
                                countryRoot.get(REGION),
                                countryRoot.get(SURFACE_AREA),
                                countryRoot.get(INDEP_YEAR),
                                countryRoot.get(POPULATION),
                                countryRoot.get(LIFE_EXPECTANCY),
                                countryRoot.get(GNP),
                                countryRoot.get(GNPOLD),
                                countryRoot.get(LOCAL_NAME),
                                countryRoot.get(GOVERNMENT_FORM),
                                countryRoot.get(HEAD_OF_STATE),
                                countryRoot.get(COUNTRY_CODE2),
                                countryCityJoin.get(CITY_NAME))
                .groupBy(countryRoot.get(CONTINENT), countryRoot.get(REGION),
                        countryRoot.get(COUNTRY_CODE), countryRoot.get(COUNTRY_NAME))
                .orderBy(cb.asc(countryRoot.get(CONTINENT)), cb.asc(countryRoot.get(REGION)),
                        cb.asc(countryRoot.get(COUNTRY_CODE)));

    }

}
