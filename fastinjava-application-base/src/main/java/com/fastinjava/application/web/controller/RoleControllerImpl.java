package com.fastinjava.application.web.controller;

import com.fastinjava.application.service.RoleService;
import com.fastinjava.framework.baseapplication.api.RoleController;
import com.fastinjava.framework.baseapplication.vo.RoleInsertVO;
import com.fastinjava.framework.baseapplication.vo.RoleListDetailVO;
import com.fastinjava.framework.baseapplication.vo.RoleListReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "角色管理", tags = {"角色管理api"})
@RestController
@RequestMapping("/role")
public class RoleControllerImpl implements RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    @Override
    public JsonResult<PageResult<RoleListDetailVO>> list(@RequestBody RoleListReqVO roleListReqVO) {
        PageResult<RoleListDetailVO> pageResult = roleService.list(roleListReqVO);
        return JsonResult.<PageResult<RoleListDetailVO>>builder().success(pageResult).build();
    }

    @PostMapping("/insert")
    @Override
    public JsonResult<Boolean> insert(@RequestBody RoleInsertVO roleInsertVO) {
        return roleService.insert(roleInsertVO) ? JsonResult.<Boolean>builder().success().build() : JsonResult.<Boolean>builder().failure("新增失败").build();
    }

    
}
