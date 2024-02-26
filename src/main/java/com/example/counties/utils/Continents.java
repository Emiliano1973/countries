package com.example.counties.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Continents
{
    //'Asia','Europe','North America','Africa','Oceania','Antarctica','South America'
    ASIA("AS","Asia"), EUROPE("EU", "Europe"), NORTH_AMERICA("NA","North America"),
    SOUTH_AMERICA("SA","South America"), AFRICA("AF","Africa"), OCEANIA("OC","Oceania"),
    ANTARCTICA("AN","Antarctica");

    private final String code;
    private final String continentName;

    Continents(final String code,final String continentName){
        this.code=code;
        this.continentName=continentName;
    }

    public String getContinentName() {
        return continentName;
    }

    public String getCode() {
        return code;
    }

    public static Optional<Continents> getContinentByName(final String continentName){
        return Stream.of(values()).filter(c->c.getContinentName().equalsIgnoreCase(continentName)).findFirst();
    }
    public static Optional<Continents> getContinentByCode(final String continentCode){
        return Stream.of(values()).filter(c->c.getCode().equalsIgnoreCase(continentCode)).findFirst();
    }
    public static Collection<String> getAllContinentNames(){
        return Stream.of(values()).map(Continents::getContinentName).collect(Collectors.toList());
    }
}
