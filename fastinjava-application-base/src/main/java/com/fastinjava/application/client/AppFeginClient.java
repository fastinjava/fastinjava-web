package com.fastinjava.application.client;

import com.fastdevelopinjava.framework.system.api.dto.ApplicationDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-system/application")
public interface AppFeginClient {
    @PostMapping("/getList")
    ResultDTO<PageDTO<ApplicationDTO>> getList(@RequestBody ApplicationReqDTO applicationReqDTO);

    @PostMapping("/insert")
    ResultDTO<Boolean> insert(@RequestBody ApplicationInsertDTO applicationInsertDTO);
}
