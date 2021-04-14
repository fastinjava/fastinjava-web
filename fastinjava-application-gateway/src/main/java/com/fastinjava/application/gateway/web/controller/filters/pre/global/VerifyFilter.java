//package com.fastinjava.application.gateway.web.controller.filters.pre.global;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.lang.Assert;
//import cn.hutool.core.text.StrBuilder;
//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.crypto.asymmetric.RSA;
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
//import com.fastinjava.application.gateway.client.OauthInfoFeginClient;
//import com.fastinjava.framework.common.consts.FJConstans;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.jwt.crypto.sign.RsaVerifier;
//import org.springframework.stereotype.Component;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.net.URI;
//import java.security.interfaces.RSAPublicKey;
//import java.util.Optional;
//
//@Slf4j
//@Component
//public class VerifyFilter implements GlobalFilter, Ordered {
//
//    @Resource
//    private OauthInfoFeginClient oauthInfoFeginClient;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        try {
//            ServerHttpRequest request = exchange.getRequest();
//            MultiValueMap<String, String> queryParams = request.getQueryParams();
//            URI uri = request.getURI();
//            String query = uri.getQuery();
//            HttpMethod method = request.getMethod();
//
//            String authorization = Optional.ofNullable(request.getHeaders().get(FJConstans.authorization)).map(authorizations -> authorizations.get(0)).orElseThrow(() -> new RuntimeException("未携带token"));
//            authorization = authorization.substring(FJConstans.bearer.length());
//            //解析并验证token是否合法
//            Jwt jwt = JwtHelper.decode(authorization);
//            JSONObject accessObj = JSON.parseObject(jwt.getClaims());
//            String clientId = accessObj.getString("loginClient");
//            log.info("accessObj = {}", JSONUtil.toJsonPrettyStr(accessObj));
//            ResultDTO<JSONObject> resultDTO = oauthInfoFeginClient.oauthClientExtinfo(new JSONObject().fluentPut("clientId", clientId));
//            Assert.isTrue(resultDTO.getSuccess() && ObjectUtil.isNotEmpty( resultDTO.getData()),"客户端不存在，客户端非法 , clientId = {}",clientId);
//            JSONObject clientExtInfo = resultDTO.getData();
//            String accessKey = clientExtInfo.getString("accessKey");
//            String accessSecret = clientExtInfo.getString("accessSecret");
//            String privateKeyBase64 = clientExtInfo.getString("privateKeyBase64");
//            String publicKeyBase64 = clientExtInfo.getString("publicKeyBase64");
//
//            RSA rsa = new RSA(privateKeyBase64, publicKeyBase64);
//
//            //这一步不报错基本上token就是非法的
//            JwtHelper.decodeAndVerify(authorization,new RsaVerifier((RSAPublicKey) rsa.getPublicKey()));
//
//            long ldistanceTime = DateUtil.currentSeconds() - accessObj.getLong("exp");
//            if (ldistanceTime <= 0 ){
//
//            }else {
//                StrBuilder ldistanceBuilder = StrBuilder.create("token已过期\n");
//                ldistanceBuilder.append("过期时间为 = " + accessObj.getString("expDatetime"));
//                ldistanceBuilder.append("\n");
//                ldistanceBuilder.append("当前时间为 = " + DateUtil.formatDateTime(DateUtil.date()));
//                throw new RuntimeException(ldistanceBuilder.toStringAndReset());
//            }
//
//
//            exchange.getAttributes().put("CURRENT_CLIENT_ID", clientId);
//
//            return chain.filter(exchange);
//        } catch (Exception e) {
//            log.error("VerifyFilter#filter error , errorMessage = {}", e.getMessage());
//            throw new RuntimeException(StrUtil.format("VerifyFilter#filter error , errorMessage = {}", e.getMessage()));
//        }
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
//        return 0;
//    }
//}
