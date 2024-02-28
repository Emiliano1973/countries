package com.example.counties.utils;

public class ContinentsConverter implements org.springframework.core.convert.converter.Converter<String, Continents>{
    @Override
    public Continents convert(final String source) {
        return Continents.getContinentByCode(source).orElseThrow(()->new IllegalArgumentException("Error parameter in input with value ["+source+"] is not a continent"));
    }
}
