package com.example.counties.dao.repos;

import com.example.counties.entities.CountryLanguage;
import com.example.counties.entities.CountryLanguagePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguagesRepository extends JpaRepository<CountryLanguage, CountryLanguagePk> {
}
