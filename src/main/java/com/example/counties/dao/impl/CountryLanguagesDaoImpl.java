package com.example.counties.dao.impl;

import com.example.counties.dao.CountryLanguagesDao;
import com.example.counties.dtos.CountryLanguageDto;
import com.example.counties.entities.Country;
import com.example.counties.entities.CountryLanguage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.example.counties.utils.CountryLanguageFieldNames.*;

@Repository
public class CountryLanguagesDaoImpl implements CountryLanguagesDao {

    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CountryLanguageDto> getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial) {

        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CountryLanguageDto> criteriaQuery=cb.createQuery(CountryLanguageDto.class);
        Root<CountryLanguage> countryLanguageRoot=criteriaQuery.from(CountryLanguage.class);
        Join<CountryLanguage, Country> languageCountryJoin=countryLanguageRoot.join(COUNTRY, JoinType.INNER);
        criteriaQuery.multiselect(languageCountryJoin.get("name"), countryLanguageRoot.get(COUNTRY_LANGUAGE_PK)
                        .get(LANGUAGE),
                countryLanguageRoot.get(PERGENTAGE), countryLanguageRoot.get(OFFICIAL_LANGUAGE))
                .where(cb.equal(countryLanguageRoot.get(COUNTRY_LANGUAGE_PK)
                .get(COUNTRY_CODE), countryCode), cb.equal(countryLanguageRoot.get(OFFICIAL_LANGUAGE), isOffcial));
        return this.em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Collection<CountryLanguageDto> getLanguagesByCountryId(String countryCode) {
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CountryLanguageDto> criteriaQuery=cb.createQuery(CountryLanguageDto.class);
        Root<CountryLanguage> countryLanguageRoot=criteriaQuery.from(CountryLanguage.class);
        Join<CountryLanguage, Country> languageCountryJoin=countryLanguageRoot.join(COUNTRY, JoinType.INNER);
        //tring countryName, String language, double percentage, boolean official
        criteriaQuery.multiselect(languageCountryJoin.get("name"), countryLanguageRoot.get(COUNTRY_LANGUAGE_PK)
                        .get(LANGUAGE),
                countryLanguageRoot.get(PERGENTAGE), countryLanguageRoot.get(OFFICIAL_LANGUAGE))
                .where(cb.equal(countryLanguageRoot.get(COUNTRY_LANGUAGE_PK)
                        .get(COUNTRY_CODE), countryCode));
        return this.em.createQuery(criteriaQuery).getResultList();
    }
}
