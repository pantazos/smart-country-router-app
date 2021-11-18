package com.development.test.service;

import com.development.test.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

@Service
public class CountryServiceImpl implements CountryService {

    @Value("${url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map<String, Country> getCountries() {
        ResponseEntity<List<Country>> response = restTemplate.exchange(url, GET, null, new ParameterizedTypeReference<>() {});

        List<Country> countries = response.getBody();

        return countries.stream().collect(Collectors.toMap(Country::getCountryName, c -> c));
    }
}
