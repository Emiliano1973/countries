package com.example.counties.configuration;

import com.example.counties.utils.ContinentsConverter;
import com.example.counties.utils.RegionConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ContinentsConverter());
        registry.addConverter(new RegionConverter());
    }
}
