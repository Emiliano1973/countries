package com.example.counties.dao.impl;

import com.example.counties.dao.CountryDao;
import com.example.counties.dtos.CountryDto;
import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.PaginatorDtoBuilder;
import com.example.counties.entities.City;
import com.example.counties.entities.City_;
import com.example.counties.entities.Country;
import com.example.counties.entities.Country_;
import com.example.counties.utils.Continents;
import com.example.counties.utils.Regions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.counties.entities.Country_.capital;
import static com.example.counties.entities.Country_.continent;
import static com.example.counties.entities.Country_.countryCode;
import static com.example.counties.entities.Country_.countryCode2;
import static com.example.counties.entities.Country_.gnp;
import static com.example.counties.entities.Country_.gnpOld;
import static com.example.counties.entities.Country_.governmentForm;
import static com.example.counties.entities.Country_.headOfState;
import static com.example.counties.entities.Country_.indepYear;
import static com.example.counties.entities.Country_.lifeExpectancy;
import static com.example.counties.entities.Country_.localName;
import static com.example.counties.entities.Country_.name;
import static com.example.counties.entities.Country_.population;
import static com.example.counties.entities.Country_.region;
import static com.example.counties.entities.Country_.surfaceArea;


@Repository
public class CountryDaoImpl implements CountryDao {
    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CountryDto> findAll() {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByPage(final int page, final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countAll(cb);
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0).setPageSize(pageSize)
                    .setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages).setPageSize(pageSize)
                .setTotalElements(counts).setElements(countryDtos).createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByContinent(final Continents continent) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(cb.upper(countryRoot.get(Country_.continent)),
                        continent.getContinentName().toUpperCase()));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public PaginationDto findByContinentByPage(final Continents continent, final int page,
                                               final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countByFieldName(cb,Country_.continent, continent.getContinentName());
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0).setPageSize(pageSize)
                    .setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(Country_.continent), continent.getContinentName()));
        TypedQuery<CountryDto> query = this.em.createQuery(countryDtoCriteriaQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult((page - 1) * pageSize);
        Collection<CountryDto> countryDtos = query.getResultList();
        int numPages = (counts / pageSize);
        if ((counts % pageSize) > 0) {
            numPages += 1;
        }
        return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(numPages).setPageSize(pageSize)
                .setTotalElements(counts).setElements(countryDtos).createPaginatorDto();
    }

    @Override
    public Collection<CountryDto> findByRegion(final Regions region) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(Country_.region), region.getRegionName()));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();


    }

    @Override
    public PaginationDto findByRegionByPage(final Regions region, final int page, final int pageSize) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        int counts = countByFieldName(cb, Country_.region, region.getRegionName());
        if (counts == 0) {
            return new PaginatorDtoBuilder().setCurrentPage(page).setTotalPages(0).setPageSize(pageSize)
                    .setTotalElements(counts).setElements(new ArrayList<>()).createPaginatorDto();
        }
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.equal(countryRoot.get(Country_.region), region.getRegionName()));
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
    public Collection<CountryDto> findByPopulation(final Integer population) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        countryDtoCriteriaQuery
                .where(cb.lessThanOrEqualTo(countryRoot.get(Country_.population), population));
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    @Override
    public Collection<CountryDto> findByIndep(final Boolean isIndep) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<CountryDto> countryDtoCriteriaQuery = cb.createQuery(CountryDto.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        Join<Country, City> countryCityJoin = countryRoot.join(capital, JoinType.INNER);
        setCountryField(cb, countryDtoCriteriaQuery, countryRoot, countryCityJoin);
        if(isIndep.booleanValue()) {
            countryDtoCriteriaQuery
                    .where(cb.isNotNull(countryRoot.get(indepYear)));
        }else {
            countryDtoCriteriaQuery
                    .where(cb.isNull(countryRoot.get(indepYear)));
        }
        return this.em.createQuery(countryDtoCriteriaQuery).getResultList();
    }

    private int countAll(final CriteriaBuilder cb ) {
        CriteriaQuery<Long> countryDtoCriteriaQuery = cb.createQuery(Long.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        countryDtoCriteriaQuery.select(cb.count(countryRoot.get(countryCode)));
        return this.em.createQuery(countryDtoCriteriaQuery).getSingleResult().intValue();
    }

    private int countByFieldName(final CriteriaBuilder cb , final SingularAttribute<Country, ?> fieldName, final String value) {
        CriteriaQuery<Long> countryDtoCriteriaQuery = cb.createQuery(Long.class);
        Root<Country> countryRoot = countryDtoCriteriaQuery.from(Country.class);
        countryDtoCriteriaQuery.select(cb.count(countryRoot.get(countryCode)))
                .where(cb.equal(countryRoot.get(fieldName), value));
        return this.em.createQuery(countryDtoCriteriaQuery).getSingleResult().intValue();
    }


    private void setCountryField(final CriteriaBuilder cb ,final CriteriaQuery<CountryDto> countryDtoCriteriaQuery,
                                 final Root<Country> countryRoot, final Join<Country, City> countryCityJoin){
        countryDtoCriteriaQuery.multiselect
                        (countryRoot.get(countryCode),
                                countryRoot.get(name),
                                countryRoot.get(continent),
                                countryRoot.get(region),
                                countryRoot.get(surfaceArea),
                                countryRoot.get(indepYear),
                                countryRoot.get(population),
                                countryRoot.get(lifeExpectancy),
                                countryRoot.get(gnp),
                                countryRoot.get(gnpOld),
                                countryRoot.get(localName),
                                countryRoot.get(governmentForm),
                                countryRoot.get(headOfState),
                                countryRoot.get(countryCode2),
                                countryCityJoin.get(City_.NAME))
                .groupBy(countryRoot.get(continent), countryRoot.get(region),
                        countryRoot.get(countryCode), countryRoot.get(name))
                .orderBy(cb.asc(countryRoot.get(continent)), cb.asc(countryRoot.get(region)),
                        cb.asc(countryRoot.get(countryCode)));
    }

}
