package com.fastinjava.application.gateway.rabbitmq.listeners;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fastdevelopinjava.framework.system.api.dto.gatewayconfig.GatewayDefineDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.gateway.client.GatewayDefineFeginClient;
import com.fastinjava.application.gateway.service.NacosDynamicRouteService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RabbitListener(queues = "TestDirectQueue")
public class RouterInfoRefreshListener {

    @Resource
    private GatewayDefineFeginClient gatewayDefineFeginClient;

    @Autowired
    private NacosDynamicRouteService nacosDynamicRouteService;

    @RabbitHandler
    public void process(Map testMessage) {
        try {

            ResultDTO<List<GatewayDefineDTO>> listResultDTO = gatewayDefineFeginClient.findAll();

            if (listResultDTO.getSuccess() && ObjectUtil.isNotEmpty(listResultDTO)){
                List<GatewayDefineDTO> gatewayDefineDTOList = listResultDTO.getData();
                if (CollectionUtil.isNotEmpty(gatewayDefineDTOList)){
                    nacosDynamicRouteService.clearRoute();
                    gatewayDefineDTOList.forEach(gatewayDefineDTO -> {
                        log.info("gatewayDefineDTO = {}", JSONUtil.toJsonPrettyStr(gatewayDefineDTO));
                        addRouter(gatewayDefineDTO);
                    });
                    nacosDynamicRouteService.publish();
                }
            }

        } catch (Exception e) {
            log.info("出错了");
        }
    }

    public void addRouter(GatewayDefineDTO gatewayDefineDTO){
        try {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId(gatewayDefineDTO.getId());
            routeDefinition.setUri(getUri(gatewayDefineDTO.getUri()));
            routeDefinition.setOrder(0);
            String filters = gatewayDefineDTO.getFilters();
            String predicates = gatewayDefineDTO.getPredicates();
            List<PredicateDefinition> predicateDefinitionList = Lists.newArrayList();
            if (StrUtil.isNotBlank(predicates)){
                JSONObject jsonObject = JSONUtil.parseObj(predicates);
                String path = jsonObject.getStr("Path");
                if (StrUtil.isNotBlank(path)){
                    PredicateDefinition pathPredicate = new PredicateDefinition();
                    pathPredicate.setName("Path");
                    Map<String, String> pathPredicateMap = new HashMap(16);
                    pathPredicateMap.put("pattern", path);
                    pathPredicate.setArgs(pathPredicateMap);
                    predicateDefinitionList.add(pathPredicate);
                }
            }
            routeDefinition.setPredicates(predicateDefinitionList);
            nacosDynamicRouteService.addRoute(routeDefinition);
        } catch (Exception e) {
            log.error("添加路由信息出错");
        }
    }


    public static URI getUri(String uriStr) {
        URI uri = null;
        if (uriStr.startsWith("http")) {
            uri = UriComponentsBuilder.fromHttpUrl(uriStr).build().toUri();
        } else {
            uri = UriComponentsBuilder.fromUriString(uriStr).build().toUri();
        }

        return uri;
    }


}
