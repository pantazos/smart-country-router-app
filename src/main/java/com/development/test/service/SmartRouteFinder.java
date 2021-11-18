package com.development.test.service;

import com.development.test.domain.Country;
import com.development.test.exception.RouteNotFoundException;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

public class SmartRouteFinder implements Serializable {

    // objects
    private final Country originCountry;
    private final Country destinationCountry;

    // advanced collections
    private final Map<String, Country> countriesMap;
    private Set<String> visitedCountries = new HashSet<>();
    private List<LinkedList<Country>> routes = new LinkedList<>();

    // construct the objects
    public SmartRouteFinder(Country originCountry, Country destinationCountry, Map<String, Country> countriesMap) {
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.countriesMap = countriesMap;
    }

    public List<Country> findSmartRoute() {
        LinkedList<Country> firstRoute = new LinkedList<>();
        firstRoute.add(originCountry);

        this.routes.add(firstRoute);
        this.visitedCountries.add(originCountry.getCountryName());

        return expandSmartRoutes();
    }

    private List<Country> expandSmartRoutes() {
        List<LinkedList<Country>> newRoutes = new ArrayList<>();

        for (LinkedList<Country> route : routes) {
            Country lastCountry = route.getLast();
            if (CollectionUtils.isEmpty(lastCountry.getBordersList())) {
                continue;
            }

            if (lastCountry.getBordersList().contains(destinationCountry.getCountryName())) {
                route.add(destinationCountry);
                return route;
            }

            for (String country : lastCountry.getBordersList()) {
                if (visitedCountries.contains(country)) {
                    continue;
                } else {
                    visitedCountries.add(country);
                }
                LinkedList<Country> newRoute = new LinkedList<>(route);
                newRoute.add(countriesMap.get(country));
                newRoutes.add(newRoute);
            }
        }
        if (visitedCountries.size() == countriesMap.size() || newRoutes.size() == 0) {
            throw new RouteNotFoundException();
        }

        routes = newRoutes;

        return expandSmartRoutes();
    }
}
