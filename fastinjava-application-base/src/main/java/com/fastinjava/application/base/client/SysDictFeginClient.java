package com.fastinjava.application.base.client;

import com.fastdevelopinjava.framework.system.api.dto.SysDictDTO;
import com.fastdevelopinjava.framework.system.api.dto.SysDictInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.SysDictReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.framework.common.consts.ServiceList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServiceList.service_gateway, path = "/gateway/fastdevelopinjava-service-system/sys_dict")
public interface SysDictFeginClient {

    @PostMapping("/getList")
    public ResultDTO<PageDTO<SysDictDTO>> getList(@RequestBody SysDictReqDTO sysDictReqDTO);

    @PostMapping("/insert")
    public ResultDTO<Boolean> insert(SysDictInsertDTO sysDictInsertDTO);
}
