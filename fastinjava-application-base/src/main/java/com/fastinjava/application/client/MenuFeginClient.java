package com.fastinjava.application.client;

import com.fastdevelopinjava.framework.system.api.dto.*;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-system/menu")
public interface MenuFeginClient {
    @PostMapping("/getList")
    public ResultDTO<PageDTO<MenuDTO>> getList(@RequestBody MenuReqDTO menuReqDTO);
    @PostMapping("/insert")
    public ResultDTO<Boolean> insert(@RequestBody MenuInsertDTO menuInsertDTO);
}
