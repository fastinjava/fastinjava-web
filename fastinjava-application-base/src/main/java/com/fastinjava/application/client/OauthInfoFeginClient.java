package com.fastinjava.application.client;

import com.fastdevelopinjava.framework.system.api.dto.OauthDetailReqDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsUpdateDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-system/auth")
public interface OauthInfoFeginClient {

    @PostMapping(value = "/getOne",name = "获取客户端详情")
    public ResultDTO<OauthDetailsDTO> getOne(@RequestBody OauthDetailReqDTO oauthDetailReqDTO);

    @PostMapping(value = "/getList",name = "获取客户端列表")
    public ResultDTO<PageDTO<OauthDetailsDTO>> getList(@RequestBody OauthDetailReqDTO oauthDetailReqDTO);

    @PostMapping(value = "/update",name = "更新客户端信息")
    public ResultDTO<Boolean> update(@RequestBody OauthDetailsUpdateDTO oauthDetailsUpdateDTO);


    @PostMapping(value = "/insert",name = "新增客户端信息")
    public ResultDTO<Boolean> insert(@RequestBody OauthDetailsInsertDTO oauthDetailsInsertDTO);
}
