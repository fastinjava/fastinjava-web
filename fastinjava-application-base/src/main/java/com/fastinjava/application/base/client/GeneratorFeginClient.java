package com.fastinjava.application.base.client;

import com.fastdevelopinjava.framework.codegen.api.dto.GenCodePreviewReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-codegen/generator")
public interface GeneratorFeginClient {
    @PostMapping("/preview")
    public ResultDTO<Map<String, Object>> preview(@RequestBody GenCodePreviewReqDTO genCodePreviewReqDTO);

    @PostMapping("/generatorCodeV1")
    public ResultDTO<String> generatorCodeV1(@RequestBody GenCodePreviewReqDTO genCodePreviewReqDTO);

}
