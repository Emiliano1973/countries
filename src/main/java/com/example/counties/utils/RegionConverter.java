package com.example.counties.utils;

public class RegionConverter implements org.springframework.core.convert.converter.Converter<String, Regions>{
    @Override
    public Regions convert(String source) {
        return  Regions.getRegionByString(source).orElseThrow(()->new IllegalArgumentException(
                "Error parameter in input with value ["+source+"] is not a region"));
    }
}
