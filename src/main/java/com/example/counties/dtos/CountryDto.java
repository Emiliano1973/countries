package com.example.counties.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public record CountryDto(
                           @JsonProperty("countryCode")
                           String countryCode,
                           @JsonProperty("countryName")
                           String name,
                           @JsonProperty("continent")
                           String continent,
                           @JsonProperty("region")
                           String region,
                           @JsonProperty("surfaceArea")
                           Double surfaceArea,
                           @JsonProperty("indepYear")
                           Integer indepYear,
                           @JsonProperty("population")
                           Integer population,
                           @JsonProperty("lifeExpectancy")
                           Double lifeExpectancy,
                           @JsonProperty("gnp")
                           Double gnp,
                           @JsonProperty("gnpOld")
                           Double gnpOld,
                           @JsonProperty("localName")
                           String localName ,
                           @JsonProperty("governmentForm")
                           String governmentForm ,
                           @JsonProperty("headOfState")
                           String headOfState ,
                           @JsonProperty("countryCode2")
                           String countryCode2,
                           @JsonProperty("capitalName")
                           String capital) implements Serializable {

}
