package com.fastinjava.application.base.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fastinjava.application.base.service.UserService;
import com.fastinjava.framework.baseapplication.api.UserController;
import com.fastinjava.framework.baseapplication.vo.UserInsertVO;
import com.fastinjava.framework.baseapplication.vo.UserListDetailVO;
import com.fastinjava.framework.baseapplication.vo.UserReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Api(value = "用户接口", tags = {"用户接口管理api"})
@RequestMapping("/user")
@RestController
public class UserControllerImpl implements UserController {

    @Resource
    private UserService userService;

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<UserListDetailVO>> list(@RequestBody UserReqVO userReqVO) {
        return JsonResult.<PageResult<UserListDetailVO>>builder().success(userService.list(userReqVO)).build();
    }

    @Override
    @PostMapping("/insert")
    public JsonResult<Boolean> insert(@RequestBody UserInsertVO userInsertVO) {
        return JsonResult.<Boolean>builder().success(userService.insert(userInsertVO)).build();
    }


    /**
     * 根据用户username获取用户在具体某个客户端下的菜单
     * @return
     */
    @PostMapping("/getUserMenus")
    public JsonResult<JSONObject> getUserMenus(@RequestBody JSONObject jsonObject){
        String username = jsonObject.getString("username");
        String clientId = jsonObject.getString("clientId");
        return JsonResult.<JSONObject>builder().success(new JSONObject()).build();
    }

}
