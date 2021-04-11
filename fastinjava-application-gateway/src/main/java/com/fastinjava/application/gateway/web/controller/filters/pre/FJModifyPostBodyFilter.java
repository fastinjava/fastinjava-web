package com.fastinjava.application.gateway.web.controller.filters.pre;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fastinjava.application.gateway.utils.GatewayFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.Charset;

@Slf4j
public class FJModifyPostBodyFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info(this.getClass().getName());

        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        HttpMethod method = request.getMethod();
        HttpHeaders headers = request.getHeaders();
        if (HttpMethod.POST == method) {
            String type = headers.getContentType().getType();
            if (ObjectUtil.isNotEmpty(type) && MediaType.APPLICATION_JSON_UTF8_VALUE.contains(type)) {
                log.info("uri = {} ,method  = {} , headers = {} ", uri, method.toString(), headers);
                return DataBufferUtils.join(exchange.getRequest().getBody())
                        .flatMap(dataBuffer -> {
                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(bytes);
                            String originBody = StrUtil.str(bytes, Charset.defaultCharset());
                            log.info("原始post请求参数, originBody = {} ", originBody);//打印请求参数
                            exchange.getAttributes().put("REQUEST_BODY", originBody);

                            JSONObject jsonObject = JSON.parseObject(originBody);


                            jsonObject.fluentPut("requestId", IdUtil.fastSimpleUUID());

                            String newBody = JSON.toJSONString(jsonObject);
                            log.info("组装之后的请求参数, newBody = {} ", newBody);
                            return GatewayFilterUtil.resetRequestChainFilter(newBody, exchange, chain);
                        });
            }
        } else if (HttpMethod.GET == method) {
            MultiValueMap<String, String> queryParams = request.getQueryParams();
            log.info("GET请求参数: {} ", queryParams);
        }
        return chain.filter(exchange);
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 12;
    }
}
