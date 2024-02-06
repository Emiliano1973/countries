package com.example.counties.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Continents
{
    //'Asia','Europe','North America','Africa','Oceania','Antarctica','South America'
    ASIA("Asia"), EUROPE("Europe"), NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South America"), AFRICA("Africa"), OCEANIA("Oceania"),
    ANTARCTICA("Antarctica");

    private final String continentName;
    Continents(String continentName){
        this.continentName=continentName;
    }

    public String getContinentName() {
        return continentName;
    }

    public static Optional<Continents> getContinentByString(final String continentName){
        return Stream.of(values()).filter(c->c.getContinentName().equalsIgnoreCase(continentName)).findFirst();
    }
    public static Collection<String> getAllContinents(){
        return Stream.of(values()).map(Continents::getContinentName).collect(Collectors.toList());
    }
}
