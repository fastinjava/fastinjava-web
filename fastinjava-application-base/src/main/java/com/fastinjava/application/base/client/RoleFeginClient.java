package com.fastinjava.application.base.client;

import com.fastdevelopinjava.framework.ucenter.api.dto.GrantRoleMenuDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-ucenter/role")
public interface RoleFeginClient {

    @PostMapping(value = "/getList")
    ResultDTO<PageDTO<RoleDTO>> getList(@RequestBody RoleReqDTO roleReqDTO);

    @PostMapping(value = "/insert")
    ResultDTO<Boolean> insert(@RequestBody RoleCreateDTO roleCreateDTO);

    @PostMapping("/grantRoleMenus")
    ResultDTO<Boolean> grantRoleMenus(@RequestBody GrantRoleMenuDTO grantRoleMenuDTO);

    @PostMapping("/getOne")
    ResultDTO<RoleDTO> getOne(@RequestBody RoleReqDTO roleReqDTO);
}
