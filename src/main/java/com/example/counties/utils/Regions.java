package com.example.counties.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Regions {
    CARIBBEAN("Caribbean"),
    SOUTHERN_AND_CENTRAL_ASIA("Southern and Central Asia"),
    CENTRAL_ASIA("Central Africa"),
    SOUTHERN_EUROPE("Southern Europe"),
    MIDDLE_EAST("Middle East"),
    SOUTH_AMERICA("South America"),
    POLYNESIA("Polynesia"),
    ANTARCTICA("Antarctica"),
    AUSTRALIA_AND_NEW_ZEALAND("Australia and New Zealand"),
    WESTERN_EUROPE("Western Europe"),
    EASTERN_AFRICA("Eastern Africa"),
    WESTERN_AFRICA("Western Africa"),
    EASTERN_EUROPE("Eastern Europe"),
    CENTRAL_AMERICA("Central America"),
    NORTH_AMERICA("North America"),
    SOURTHEAST_ASIA("Southeast Asia"),
    SOURTHERN_AFRICA("Southern Africa"),
    EASTERN_ASIA("Eastern Asia"),
    NORDIC_COUNTRIES("Nordic Countries"),
    NORTHERN_AFRICA("Northern Africa"),
    BALTIC_COUNTRIES("Baltic Countries"),
    MELANESIA("Melanesia"),
    MICRONESIA("Micronesia"),
    BRITISH_ISLANDS("British Islands"),
    MICRONESIA_CARIBBEAN("Micronesia/Caribbean")    ;

    private final String regionName;

    Regions(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public static Collection<String> getAllRegions(){
      return Stream.of(values()).map(Regions::getRegionName).collect(Collectors.toList());
    }

    public static Optional<Regions> getRegionByString(final String regionName){
        return Stream.of(values()).filter(c->c.getRegionName().equalsIgnoreCase(regionName)).findFirst();
    }

}
