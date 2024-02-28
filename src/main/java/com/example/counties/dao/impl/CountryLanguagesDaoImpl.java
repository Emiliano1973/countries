package com.example.counties.dao.impl;

import com.example.counties.dao.CountryLanguagesDao;
import com.example.counties.dtos.CountryLanguageDto;
import com.example.counties.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.example.counties.entities.CountryLanguagePk_.*;
import static com.example.counties.entities.CountryLanguage_.country;
import static com.example.counties.entities.CountryLanguage_.countryLanguagePk;
import static com.example.counties.entities.CountryLanguage_.officialLanguage;
import static com.example.counties.entities.CountryLanguage_.percentage;

@Repository
public class CountryLanguagesDaoImpl implements CountryLanguagesDao {

    @PersistenceContext
   private EntityManager em;

    @Override
    public Collection<CountryLanguageDto> getLanguagesByCountryIdAndIsOfficial(String countryCode, Boolean isOffcial) {
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CountryLanguageDto> criteriaQuery=cb.createQuery(CountryLanguageDto.class);
        Root<CountryLanguage> countryLanguageRoot=criteriaQuery.from(CountryLanguage.class);
        Join<CountryLanguage, Country> languageCountryJoin=countryLanguageRoot.join(country, JoinType.INNER);
        criteriaQuery.multiselect(languageCountryJoin.get(Country_.name),
                        countryLanguageRoot.get(countryLanguagePk)
                        .get(language),
                countryLanguageRoot.get(percentage), countryLanguageRoot.get(officialLanguage))
                .where(cb.equal(countryLanguageRoot.get(countryLanguagePk)
                .get(CountryLanguagePk_.countryCode), countryCode),
                        cb.equal(countryLanguageRoot.get(officialLanguage), isOffcial));
        return this.em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Collection<CountryLanguageDto> getLanguagesByCountryId(String countryCode) {
        CriteriaBuilder cb=this.em.getCriteriaBuilder();
        CriteriaQuery<CountryLanguageDto> criteriaQuery=cb.createQuery(CountryLanguageDto.class);
        Root<CountryLanguage> countryLanguageRoot=criteriaQuery.from(CountryLanguage.class);
        Join<CountryLanguage, Country> languageCountryJoin=countryLanguageRoot.join(country, JoinType.INNER);
        criteriaQuery.multiselect(languageCountryJoin.get(Country_.name), countryLanguageRoot.get(countryLanguagePk)
                        .get(language),
                countryLanguageRoot.get(percentage), countryLanguageRoot.get(officialLanguage))
                .where(cb.equal(countryLanguageRoot.get(countryLanguagePk)
                        .get(CountryLanguagePk_.countryCode), countryCode));
        return this.em.createQuery(criteriaQuery).getResultList();
    }
}
