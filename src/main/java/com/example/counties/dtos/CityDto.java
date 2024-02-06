package com.example.counties.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record CityDto(
        @JsonProperty("cityName")
        String name,
        @JsonProperty("district")
        String district,
        @JsonProperty("population")
        Integer population,
        @JsonProperty("countryName")
        String countryName) implements Serializable {
}
