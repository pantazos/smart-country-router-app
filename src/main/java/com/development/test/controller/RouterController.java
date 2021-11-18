package com.development.test.controller;

import com.development.test.domain.RouteRes;
import com.development.test.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouterController {

    // dependency injection
    @Autowired
    private RouterService routerService;

    @GetMapping("/routing/{origin}/{destination}")
    public RouteRes retrieveRoute(@PathVariable String origin, @PathVariable String destination) {
        RouteRes routeResult = new RouteRes();
        routeResult.setRoute(routerService.router(origin, destination));

        return routeResult;
    }
}
