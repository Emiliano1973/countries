package com.example.counties.cotrollers;

import com.example.counties.dtos.PaginationDto;
import com.example.counties.dtos.ResponseDto;
import com.example.counties.services.CountryService;
import com.example.counties.utils.Continents;
import com.example.counties.utils.Regions;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
@CacheConfig(cacheNames = "country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(final CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "country",  keyGenerator = "customKeyGenerator")
    public ResponseDto getAll(){
        return this.countryService.findAll();
    }

    @GetMapping( value ="/continent",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "country",  keyGenerator = "customKeyGenerator")
    public ResponseDto getAllByContinent(@RequestParam("continent") final Continents continent){
        return  this.countryService.findByContinent(continent);
    }

    @GetMapping( value ="/region",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "country",  keyGenerator = "customKeyGenerator")
    public ResponseDto getAllByRegion(@RequestParam("region") final Regions region){
        return this.countryService.findByRegion(region);
    }

    @GetMapping( value ="/region/pages",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "country",  keyGenerator = "customKeyGenerator")
    public PaginationDto getAllByRegionPage(@RequestParam("region") final Regions region,
                                            @RequestParam("page") final int page,
                                            @RequestParam("pageSize") final int pageSize){
        return this.countryService.findByRegionByPage(region, page, pageSize);
    }

    @GetMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "country",  keyGenerator = "customKeyGenerator")
    public PaginationDto getAllByPage(@RequestParam("page")  final int page, @RequestParam("pageSize") final int pageSize){
        return this.countryService.findByPage(page, pageSize);
    }

    @GetMapping( value ="/continent/pages",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "country",  keyGenerator = "customKeyGenerator")
    public PaginationDto getByContinentPage(@RequestParam("continent") final Continents continent,
                                            @RequestParam("page") final int page,
                                            @RequestParam("pageSize") final  int pageSize) {
        return this.countryService.findByContinentByPage(continent, page, pageSize);
    }

}
