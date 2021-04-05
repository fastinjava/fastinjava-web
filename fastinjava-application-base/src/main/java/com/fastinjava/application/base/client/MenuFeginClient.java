package com.fastinjava.application.base.client;

import com.fastdevelopinjava.framework.system.api.dto.MenuDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-system/menu")
public interface MenuFeginClient {
    @PostMapping(value = "/getOne")
    public ResultDTO<MenuDTO> getOne(@RequestBody MenuReqDTO menuReqDTO);
    @PostMapping(value = "/getList",name = "获取菜单列表")
    public ResultDTO<PageDTO<MenuDTO>> getList(@RequestBody MenuReqDTO menuReqDTO);
    @PostMapping(value = "/insert",name = "新增菜单")
    public ResultDTO<Boolean> insert(@RequestBody MenuInsertDTO menuInsertDTO);

    @PostMapping("/listTree")
    public ResultDTO<List<MenuNodeDTO>> listTree(@RequestBody MenuReqDTO menuReqDTO);

}
