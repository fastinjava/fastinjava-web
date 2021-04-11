package com.fastinjava.application.gateway.utils;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

public class CachedBodyOutputMessage implements ReactiveHttpOutputMessage {
    private final DataBufferFactory bufferFactory;
    private final HttpHeaders httpHeaders;
    private Flux<DataBuffer> body = Flux.error(new IllegalStateException("The body is not set. Did handling complete with success? Is a custom \"writeHandler\" configured?"));
    private Function<Flux<DataBuffer>, Mono<Void>> writeHandler = this.initDefaultWriteHandler();

    public CachedBodyOutputMessage(ServerWebExchange exchange, HttpHeaders httpHeaders) {
        this.bufferFactory = exchange.getResponse().bufferFactory();
        this.httpHeaders = httpHeaders;
    }

    public void beforeCommit(Supplier<? extends Mono<Void>> action) {
    }

    public boolean isCommitted() {
        return false;
    }

    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }

    private Function<Flux<DataBuffer>, Mono<Void>> initDefaultWriteHandler() {
        return (body) -> {
            this.body = body.cache();
            return this.body.then();
        };
    }

    public DataBufferFactory bufferFactory() {
        return this.bufferFactory;
    }

    public Flux<DataBuffer> getBody() {
        return this.body;
    }

    public void setWriteHandler(Function<Flux<DataBuffer>, Mono<Void>> writeHandler) {
        Assert.notNull(writeHandler, "'writeHandler' is required");
        this.writeHandler = writeHandler;
    }

    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        this.body = Flux.from(body);
        return Mono.empty();
    }

    public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
        return this.writeWith(Flux.from(body).flatMap((p) -> {
            return p;
        }));
    }

    public Mono<Void> setComplete() {
        return this.writeWith(Flux.empty());
    }
}
