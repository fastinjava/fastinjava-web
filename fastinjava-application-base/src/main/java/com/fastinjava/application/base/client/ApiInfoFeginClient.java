package com.fastinjava.application.base.client;

import com.fastdevelopinjava.framework.system.api.dto.ApiInfoDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-system/apiinfo")
public interface ApiInfoFeginClient {
    @PostMapping("/getList")
    public ResultDTO<PageDTO<ApiInfoDTO>> getList(@RequestBody ApiInfoReqDTO apiInfoReqDTO);
    @PostMapping("/insert")
    public ResultDTO<Boolean> insert(@RequestBody ApiInfoInsertDTO apiInfoInsertDTO);
}
