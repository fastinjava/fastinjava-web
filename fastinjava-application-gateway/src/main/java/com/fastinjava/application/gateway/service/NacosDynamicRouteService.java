package com.fastinjava.application.gateway.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class NacosDynamicRouteService implements ApplicationEventPublisherAware {

    private static final List<RouteDefinition> ROUTE_LIST = new ArrayList<RouteDefinition>();
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher applicationEventPublisher;

    public void clearRoute() {
        for (RouteDefinition routeDefinition : ROUTE_LIST) {
            this.routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()));
        }
    }

    public void addRoute(RouteDefinition definition) {
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            ROUTE_LIST.add(definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
