package com.fastinjava.application.web.controller;

import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastinjava.application.service.MenuService;
import com.fastinjava.framework.baseapplication.api.MenuController;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "菜单管理api", tags = {"菜单管理api"})
@RestController
@Slf4j
@RequestMapping("/menu")
public class MenuControllerImpl implements MenuController {

    @Resource
    private MenuService menuService;

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<MenuListDetailVO>> list(@RequestBody MenuReqVO menuReqVO) {
        PageResult<MenuListDetailVO> pageResult = menuService.list(menuReqVO);
        return JsonResult.<PageResult<MenuListDetailVO>>builder().success(pageResult).build();
    }

    @Override
    @PostMapping("/insert")
    public JsonResult<Boolean> insert(@RequestBody MenuInsertVO menuInsertVO) {
        Boolean result = menuService.insert(menuInsertVO);
        return JsonResult.<Boolean>builder().success(result).build();
    }
}
