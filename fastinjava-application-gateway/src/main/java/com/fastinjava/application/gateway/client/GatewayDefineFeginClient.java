package com.fastinjava.application.gateway.client;

import com.fastdevelopinjava.framework.system.api.dto.gatewayconfig.GatewayDefineDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "fastdevelopinjava-service-system", path = "/fastdevelopinjava-service-system/gateway")
public interface GatewayDefineFeginClient {

    @GetMapping("/listall")
    public ResultDTO<List<GatewayDefineDTO>> findAll() ;

}
