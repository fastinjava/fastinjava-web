package com.fastinjava.application.client;

import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationReqDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationUpdateDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.NodeDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-ucenter/org")
public interface OrgFeginClient {

    @PostMapping(value = "/getList")
    ResultDTO<PageDTO<OrganizationDTO>> getList(@RequestBody OrganizationReqDTO organizationReqDTO);

    @PostMapping(value = "/getOne")
    ResultDTO<OrganizationDTO> getOne(@RequestBody OrganizationReqDTO organizationReqDTO);

    @PostMapping(value = "/listTree")
    ResultDTO<List<NodeDTO>> listTree(@RequestBody OrganizationReqDTO organizationReqDTO);

    @PostMapping(value = "/update")
    ResultDTO<Boolean> update(@RequestBody OrganizationUpdateDTO organizationUpdateDTO);


    @PostMapping("/insert")
    ResultDTO<Boolean> insert(@RequestBody OrganizationCreateDTO organizationCreateDTO);

}
