package com.fastinjava.application.gateway.web.controller.filters.pre.global;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class VerifyFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        URI uri = request.getURI();
        String query = uri.getQuery();

        HttpMethod method = request.getMethod();

        if (HttpMethod.POST == method) {

        }else if (HttpMethod.GET == method) {

            //AccessKey=5c892285-9b4b-4bd9-92f1-1d4a08b7d533&name=hello&home=world&work=java&age=12&Name=HELLO&nonce=tdegscphnx&timestamp=1618145508&sign=29433F76F5376894D6E1302BB59D36DA

            List<String> accessKey = queryParams.get("AccessKey");
            List<String> nonce = queryParams.get("nonce");
            List<String> timestamp = queryParams.get("timestamp");
            List<String> sign = queryParams.get("sign");

            if (CollectionUtil.isEmpty(accessKey) || CollectionUtil.isEmpty(nonce) || CollectionUtil.isEmpty(timestamp) || CollectionUtil.isEmpty(sign))
            {
                throw new RuntimeException("非法请求,未携带AccessKey || nonce  || timestamp || sign ");
            }else {
                //something
                Dict dict = new Dict(false);
                dict.putAll(queryParams);
                Iterator<String> iterator = dict.keySet().iterator();
                while (iterator.hasNext()){
                    String key = (String) iterator.next();
                    if ("AccessKey".equals(key)  || "sign".equals(key))
                    {
                        iterator.remove();
                        dict.remove(key);
                    }
                }

                Dict decryDict = new Dict(false);
                for (String key : dict.keySet()) {
                    Object o = dict.get(key);
                    if (o instanceof List)
                    {
                        List oo = (List) o;
                        decryDict.put(key,(String)oo.get(0));
                    }
                }


                //按照请求参数名的字母升序排列非空请求参数
                Set<String> filterKeys = MapUtil.sort(decryDict).keySet().stream().filter(StrUtil::isNotBlank).filter(key -> ObjectUtil.isNotEmpty(decryDict.get(key))).collect(Collectors.toSet());

                HashMap<String, Object> paramMap = new HashMap<>();

                if (CollectionUtil.isNotEmpty(filterKeys)){
                    for (String filterKey : filterKeys) {
                        paramMap.put(filterKey,dict.get(filterKey));
                    }
                }
                String sign_S = SecureUtil.md5(StrBuilder.create(HttpUtil.toParams(paramMap)).append("&AccessSecret=").append("123a6a36-2a5d-47da-aa56-08d158135574").toString());

                if (!StrUtil.equals(sign.get(0),sign_S))
                {
                    throw new RuntimeException("非法签名");
                }
                else {
                    long ldistanceTime = DateUtil.currentSeconds() - Long.valueOf(timestamp.get(0));
                    log.info("sign left time = {} 秒" , 30 - ldistanceTime);
                    if (!(ldistanceTime > 0 && ldistanceTime < 30))
                    {
                        throw new RuntimeException("签名过期");
                    }
                }

            }

        }
        else {

        }

        log.info("query = {}",query);
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
        return 0;
    }
}
