package com.example.counties.dao.impl;

import com.example.counties.dao.CityDao;
import com.example.counties.dtos.CityDto;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.PaginatorDtoBuilder;
import com.example.counties.entities.City;
import com.example.counties.entities.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.counties.utils.CityFieldNames.CITY_ID;
import static com.example.counties.utils.CityFieldNames.CITY_NAME;
import static com.example.counties.utils.CityFieldNames.COUNTRY;
import static com.example.counties.utils.CityFieldNames.DISTRICT;
import static com.example.counties.utils.CityFieldNames.POPULATION;
import static com.example.counties.utils.CountryFieldNames.COUNTRY_CODE;
import static com.example.counties.utils.CountryFieldNames.COUNTRY_NAME;

@Repository
public class CityDaoImpl implements CityDao {
    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CityDto> findByCountryCode(String countryCode) {
        CriteriaBuilder cb=this.em.getCriteriaBuilder();

        CriteriaQuery<CityDto> cityDtoCriteriaQuery=cb.createQuery(CityDto.class);
        Root<City> cityRoot=cityDtoCriteriaQuery.from(City.class);
        Join<City, Country> cityCountryJoin=cityRoot.join(COUNTRY, JoinType.INNER);
        setCityFields(cb, cityDtoCriteriaQuery, cityRoot, cityCountryJoin, countryCode);
        return this.em.createQuery(cityDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByCountryCodeByPage(String countryCode, int page, int pageSize) {
        int counts = countByCountryCode(countryCode);
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0).setPageSize(pageSize).setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }

        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CityDto> cityDtoCriteriaQuery=cb.createQuery(CityDto.class);
        Root<City> cityRoot=cityDtoCriteriaQuery.from(City.class);
        Join<City, Country> cityCountryJoin=cityRoot.join(COUNTRY, JoinType.INNER);
        setCityFields(cb, cityDtoCriteriaQuery, cityRoot, cityCountryJoin, countryCode);
        TypedQuery<CityDto> query=this.em.createQuery(cityDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CityDto> cityDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages).setPageSize(pageSize).setTotalElements(counts).setElements(cityDtos).createPaginatorDto();
    }


    private  void setCityFields(CriteriaBuilder cb,CriteriaQuery<CityDto>  cityDtoCriteriaQuery,
                                Root<City> cityRoot, Join<City, Country> cityCountryJoin, String countryCode){

        cityDtoCriteriaQuery.
                multiselect(cityRoot.get(CITY_NAME), cityRoot.get(DISTRICT),
                        cityRoot.get(POPULATION), cityCountryJoin.get(COUNTRY_NAME))
                .where(cb.equal(cityCountryJoin.get(COUNTRY_CODE), countryCode))
                .groupBy( cityCountryJoin.get(COUNTRY_NAME) ,cityRoot.get(DISTRICT),
                        cityRoot.get(CITY_NAME), cityRoot.get(POPULATION))
                .orderBy(cb.desc(cityCountryJoin.get(COUNTRY_NAME)), cb.asc(cityRoot.get(DISTRICT)),
                        cb.asc(cityRoot.get(CITY_NAME))) ;
    }

    private int countByCountryCode(String countryCode){
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<Long> cityDtoCriteriaQuery=cb.createQuery(Long.class);
        Root<City> cityRoot=cityDtoCriteriaQuery.from(City.class);
        Join<City, Country> cityCountryJoin=cityRoot.join(COUNTRY, JoinType.INNER);
        cityDtoCriteriaQuery.select(cb.count(cityRoot.get(CITY_ID)))
                .where(cb.equal(cityCountryJoin.get(COUNTRY_CODE), countryCode));
        return this.em.createQuery(cityDtoCriteriaQuery).getSingleResult().intValue();
    }
}
