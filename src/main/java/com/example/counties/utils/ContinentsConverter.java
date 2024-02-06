package com.example.counties.utils;

public class ContinentsConverter implements org.springframework.core.convert.converter.Converter<String, Continents>{
    @Override
    public Continents convert(String source) {
        return Continents.getContinentByString(source).orElseThrow(()->new IllegalArgumentException("Error parameter in input with value ["+source+"] is not a continent"));
    }
}
