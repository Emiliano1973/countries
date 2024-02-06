package com.example.counties.dao.repos;

import com.example.counties.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository  extends JpaRepository<City, Long> {
}
