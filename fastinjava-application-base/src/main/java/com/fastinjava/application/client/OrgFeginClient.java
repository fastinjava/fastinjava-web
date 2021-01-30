package com.fastinjava.application.client;

import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-ucenter")
public interface OrgFeginClient {

    @PostMapping(value = "/org/getList")
    ResultDTO<PageDTO<OrganizationDTO>> getList(@RequestBody OrganizationReqDTO organizationReqDTO);

    @PostMapping(value = "/org/getOne")
    ResultDTO<OrganizationDTO> getOne(@RequestBody OrganizationReqDTO organizationReqDTO);
}
