package com.fastinjava.application.gateway.utils;


import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author scc
 * @since 2020/12/29
 */
@Slf4j
public class GatewayFilterUtil {


    public static String GLOBAL_REQUEST_BODY = "REQUEST_BODY";

    private GatewayFilterUtil() {
    }

    /**
     * @param newBody
     * @param exchange
     * @param chain
     * @return
     */
    public static Mono<Void> resetRequestChainFilter(String newBody, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        Mono<String> bodyMono = Mono.just(newBody);
        BodyInserter bodyInserter = BodyInserters.fromPublisher(bodyMono, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(serverHttpRequest.getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            ServerHttpRequest decorator = getRequestDecorator(exchange, headers, outputMessage);
            return chain.filter(exchange.mutate().request(decorator).build());
        }));
    }

    /**
     * @param headersMap
     * @param exchange
     * @param chain
     * @return
     */
    public static Mono<Void> resetRequestChainFilter(Map<String, String> headersMap, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.putAll(serverHttpRequest.getHeaders());
        httpHeaders.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, httpHeaders);
        ServerHttpRequestDecorator decorator = getRequestDecorator(headersMap, exchange, httpHeaders, outputMessage);
        return chain.filter(exchange.mutate().request(decorator).build());
    }

    /**
     * @param newBody
     * @param headersMap
     * @param exchange
     * @param chain
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Mono<Void> resetRequestChainFilter(String newBody, Map<String, String> headersMap, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        Mono<String> bodyMono = Mono.just(newBody);
        BodyInserter bodyInserter = BodyInserters.fromPublisher(bodyMono, String.class);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.putAll(serverHttpRequest.getHeaders());
        httpHeaders.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, httpHeaders);
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            ServerHttpRequest decorator = getRequestDecorator(headersMap, exchange, httpHeaders, outputMessage);
            return chain.filter(exchange.mutate().request(decorator).build());
        }));
    }

    /**
     * @param newBody
     * @param exchange
     * @param chain
     * @return
     */
    public static Mono<Void> resetResponseChainFilter(String newBody, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        Mono<String> bodyMono = Mono.just(newBody);
        BodyInserter bodyInserter = BodyInserters.fromPublisher(bodyMono, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(serverHttpResponse.getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            ServerHttpResponse decorator = getResponseDecorator(exchange, headers, outputMessage);
            return chain.filter(exchange.mutate().response(decorator).build());
        }));
    }

    /**
     * @param headersMap
     * @param exchange
     * @param chain
     * @return
     */
    public static Mono<Void> resetResponseChainFilter(Map<String, String> headersMap, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(serverHttpResponse.getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        ServerHttpResponseDecorator decorator = getResponseDecorator(headersMap, exchange, headers, outputMessage);
        return chain.filter(exchange.mutate().response(decorator).build());
    }

    /**
     * @param newBody
     * @param headersMap
     * @param exchange
     * @param chain
     * @return
     */
    public static Mono<Void> resetResponseChainFilter(String newBody, Map<String, String> headersMap, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        Mono<String> bodyMono = Mono.just(newBody);
        BodyInserter bodyInserter = BodyInserters.fromPublisher(bodyMono, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(serverHttpResponse.getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            ServerHttpResponse decorator = getResponseDecorator(headersMap, exchange, headers, outputMessage);
            return chain.filter(exchange.mutate().response(decorator).build());
        }));
    }

    /**
     * @param exchange
     * @param headers
     * @param outputMessage
     * @return
     */
    protected static ServerHttpRequestDecorator getRequestDecorator(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return getRequestDecorator(null, exchange, headers, outputMessage);
    }

    /**
     * @param headersMap
     * @param exchange
     * @param headers
     * @param outputMessage
     * @return
     */
    protected static ServerHttpRequestDecorator getRequestDecorator(Map<String, String> headersMap, ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        return new ServerHttpRequestDecorator(serverHttpRequest) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (null != headersMap && !headersMap.isEmpty()) {
                    for (Map.Entry<String, String> headEntry : headersMap.entrySet()) {
                        httpHeaders.set(headEntry.getKey(), headEntry.getValue());
                    }
                }
                if (contentLength > 0L) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    /**
     * @param exchange
     * @param headers
     * @param outputMessage
     * @return
     */
    protected static ServerHttpResponseDecorator getResponseDecorator(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return getResponseDecorator(null, exchange, headers, outputMessage);
    }

    /**
     * @param headersMap
     * @param exchange
     * @param headers
     * @param outputMessage
     * @return
     */
    protected static ServerHttpResponseDecorator getResponseDecorator(Map<String, String> headersMap, ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        ServerHttpResponse response = exchange.getResponse();
        return new ServerHttpResponseDecorator(response) {

            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (null != headersMap && !headersMap.isEmpty()) {
                    for (Map.Entry<String, String> headEntry : headersMap.entrySet()) {
                        httpHeaders.set(headEntry.getKey(), headEntry.getValue());
                    }
                }
                if (contentLength > 0L) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                MediaType mediaType = response.getHeaders().getContentType();
                // 流的数据不做处理
                if (body instanceof Flux) {
                    return super.writeWith(outputMessage.getBody());
                } else {
                    log.info("========================> mediaType类型为:" + mediaType + "不做操作直接返回");
                }
                return super.writeWith(body);
            }
        };
    }

    /**
     * @param servletHttpServlet
     * @return
     */
    public static String getFormData(ServerHttpRequest servletHttpServlet) {
        HttpHeaders headers = servletHttpServlet.getHeaders();
        Charset charset = headers.getContentType().getCharset();
        String charsetName = charset.name();

        MultiValueMap<String, String> params = servletHttpServlet.getQueryParams();
        if (null == params) {
            return null;
        }
        return getFormDataString(charsetName, params);
    }

    /**
     * @param charsetName
     * @param params
     * @return
     */
    public static String getFormDataString(String charsetName, MultiValueMap<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            String key = entry.getKey();
            List<String> entryValue = entry.getValue();
            if (entryValue.size() > 1) {
                for (String value : entryValue) {
                    builder.append(key).append("=").append(encode(value, charsetName)).append("&");
                }
            } else {
                builder.append(key).append("=").append(encode(entryValue.get(0), charsetName)).append("&");
            }
        }
        String formDataString = "";
        if (builder.length() > 0) {
            formDataString = builder.substring(0, builder.length() - 1);
        }
        return formDataString;
    }

    /**
     * @param exchange
     * @return
     */
    public static void setRequestBody(ServerWebExchange exchange) {
        ServerRequest servletServlet = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
        Mono<String> requestBody = servletServlet.bodyToMono(String.class).flatMap(body -> {
            exchange.getAttributes().put(GLOBAL_REQUEST_BODY, body);
            return Mono.just(body);
        });
        requestBody.subscribe();
    }


    public static String getRequestBody(ServerWebExchange exchange) {
        Object requestBody = exchange.getAttributes().get(GLOBAL_REQUEST_BODY);
        if(null != requestBody) {
            return requestBody.toString();
        }
        return "";
    }

    /**
     * @param value
     * @param charsetName
     * @return
     */
    private static String encode(String value, String charsetName) {
        try {
            return URLEncoder.encode(value, charsetName);
        } catch (UnsupportedEncodingException e) {
            log.error("请求参数字符编码异常,value={}, charsetName={}", value, charsetName);
        }
        return value;
    }
}
