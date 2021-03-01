package com.fastinjava.application.client;

import com.fastdevelopinjava.framework.ucenter.api.dto.*;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fastdevelopinjava-service-gateway", path = "/gateway/fastdevelopinjava-service-ucenter/user")
public interface UserFeginClient {

    @PostMapping("/getOne")
    public ResultDTO<UserDTO> selectOne(@RequestBody UserReqDTO userReqDTO);

    @PostMapping("/selectList")
    public ResultDTO<PageDTO<UserDTO>> selectList(@RequestBody UserReqDTO userReqDTO);

    @PostMapping("/insert")
    public ResultDTO<Boolean> insert(@RequestBody UserCreateDTO userCreateDTO);

    @PostMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody UserUpdateDTO userUpdateDTO);

}
