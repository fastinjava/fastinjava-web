//package com.fastinjava.application.gateway.web.controller.filters.pre.global;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//public class TimeStampFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        try {
//
//            ServerHttpRequest request = exchange.getRequest();
//            HttpMethod method = request.getMethod();
//            if (HttpMethod.GET == method)
//            {
//
//                log.info("TimeStampFilter#filter get");
//
//            }else if (HttpMethod.POST == method)
//            {
//
//                log.info("TimeStampFilter#filter post");
//
//            }
//            return chain.filter(exchange);
//        } catch (Exception e) {
//            log.error("TimeStampFilter#filter error , errorMesage = {}",e.getMessage());
//            throw new RuntimeException(StrUtil.format("TimeStampFilter#filter error , errorMesage = {}",e.getMessage()));
//        }
//
//
//    }
//
//    /**
//     * Get the order value of this object.
//     * <p>Higher values are interpreted as lower priority. As a consequence,
//     * the object with the lowest value has the highest priority (somewhat
//     * analogous to Servlet {@code load-on-startup} values).
//     * <p>Same order values will result in arbitrary sort positions for the
//     * affected objects.
//     *
//     * @return the order value
//     * @see #HIGHEST_PRECEDENCE
//     * @see #LOWEST_PRECEDENCE
//     */
//    @Override
//    public int getOrder() {
//        return 2;
//    }
//}
