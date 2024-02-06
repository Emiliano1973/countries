package com.example.counties.dtos;

import java.io.Serializable;

public record CountryLanguageDto(String countryName, String language, double percentage, boolean official) implements Serializable {
}
