package com.example.counties.dao.repos;

import com.example.counties.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository  extends JpaRepository<Country, String> {
}
