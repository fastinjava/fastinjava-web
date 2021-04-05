package com.fastinjava.application.auth.client;

import com.alibaba.fastjson.JSONObject;
import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.framework.common.consts.ServiceList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = ServiceList.service_gateway, path = "/gateway/fastdevelopinjava-service-ucenter/user")
public interface UserFeginClient {

    @PostMapping("/getOne")
    ResultDTO<UserDTO> selectOne(@RequestBody UserReqDTO userReqDTO);

    @PostMapping("/getUserMenus")
    public ResultDTO<List<MenuNodeDTO>> getUserMenus(@RequestBody JSONObject jsonObject);
}
