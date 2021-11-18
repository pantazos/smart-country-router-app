package com.development.test.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {

    @JsonProperty(value = "cca3")
    private String countryName;

    @JsonProperty(value = "borders")
    private List<String> bordersList;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<String> getBordersList() {
        return bordersList;
    }

    public void setBordersList(List<String> bordersList) {
        this.bordersList = bordersList;
    }

}
