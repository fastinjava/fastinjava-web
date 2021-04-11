package com.fastinjava.application.gateway.web.controller.filters.pre;

import com.fastinjava.application.gateway.web.controller.filters.post.FJModifyResBodyFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class PreConfig {


    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(

                new Function<PredicateSpec, Route.AsyncBuilder>() {
                    @Override
                    public Route.AsyncBuilder apply(PredicateSpec predicateSpec) {

                        return predicateSpec.path("/api/demo1/**")
                                .filters(new Function<GatewayFilterSpec, UriSpec>() {
                                    @Override
                                    public UriSpec apply(GatewayFilterSpec gatewayFilterSpec) {
                                        return gatewayFilterSpec.stripPrefix(1).filters(

                                                new RequestTimeFilter(),
                                                new FJAddRequestParameterFilter(new FJAddRequestParameterFilter.Config("flag","1")),
                                                new FJModifyPostBodyFilter(),
                                                new FJModifyResBodyFilter()

                                        );
                                    }
                                }).uri("http://localhost:50001").id("demo1");
                    }
                }

        ).build();
    }

}
