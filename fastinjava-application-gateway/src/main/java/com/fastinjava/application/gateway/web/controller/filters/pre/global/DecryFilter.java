package com.fastinjava.application.gateway.web.controller.filters.pre.global;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.gateway.client.OauthInfoFeginClient;
import com.fastinjava.application.gateway.utils.GatewayFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.nio.charset.Charset;

@Component
@Slf4j
public class DecryFilter implements GlobalFilter, Ordered {

    @Resource
    private OauthInfoFeginClient oauthInfoFeginClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
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
                                String encryBody = jsonObject.getString("data");
                                String clientId = (String) exchange.getAttributes().get("CURRENT_CLIENT_ID");

                                ResultDTO<JSONObject> resultDTO = oauthInfoFeginClient.oauthClientExtinfo(new JSONObject().fluentPut("clientId", clientId));
                                Assert.isTrue(resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData()));
                                JSONObject clientExtInfo = resultDTO.getData();
                                String accessKey = clientExtInfo.getString("accessKey");
                                String accessSecret = clientExtInfo.getString("accessSecret");
                                String privateKeyBase64 = clientExtInfo.getString("privateKeyBase64");
                                String publicKeyBase64 = clientExtInfo.getString("publicKeyBase64");

                                RSA rsa = new RSA(null, publicKeyBase64);

                                try {
                                    String decryptBody = rsa.decryptStr(encryBody, KeyType.PublicKey);
                                    log.info("解密之后的请求数据decryptBody = {}",decryptBody);
                                    String newBody = JSON.toJSONString(JSON.parseObject(decryptBody).get("data"));
                                    log.info("组装之后的请求参数, newBody = {} ", newBody);
                                    return GatewayFilterUtil.resetRequestChainFilter(newBody, exchange, chain);
                                }catch (Exception e){
                                    throw new RuntimeException("解密错误 = " + e.getMessage());
                                }
                            });
                }
            } else if (HttpMethod.GET == method) {
//            MultiValueMap<String, String> queryParams = request.getQueryParams();
//            log.info("GET请求参数: {} ", queryParams);
                throw new RuntimeException("不支持GET请求解密");
            }
            return chain.filter(exchange);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
        return 1;
    }
}
