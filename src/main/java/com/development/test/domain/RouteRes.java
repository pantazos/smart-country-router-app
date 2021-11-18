package com.development.test.domain;

import java.io.Serializable;
import java.util.List;

public class RouteRes implements Serializable {

    private List<String> route;

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }
}
