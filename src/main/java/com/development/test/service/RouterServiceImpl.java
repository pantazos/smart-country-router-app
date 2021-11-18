package com.development.test.service;

import com.development.test.domain.Country;
import com.development.test.exception.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RouterServiceImpl implements RouterService {

    @Autowired
    private CountryService countryService;

    private Map<String, Country> countryMap;

    @Override
    public List<String> router(String origin, String destination) {
        Map<String, Country> countries = countryService.getCountries();

        Country originCountry = countries.get(origin);

        if (originCountry == null) {
            throw new RouteNotFoundException(); // throw an error
        }

        Country destinationCountry = countries.get(destination);

        if (destinationCountry == null) {
            throw new RouteNotFoundException(); // throw an error
        }

        if (originCountry.equals(destinationCountry)) {
            return Collections.singletonList(originCountry.getCountryName());
        }

        SmartRouteFinder routeFinder = new SmartRouteFinder(originCountry, destinationCountry, countries);
        return routeFinder.findSmartRoute().stream().map(c -> c.getCountryName()).collect(Collectors.toList());
    }

}
