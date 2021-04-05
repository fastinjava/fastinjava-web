package com.fastinjava.application.base.web.controller;

import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastinjava.application.base.service.MenuService;
import com.fastinjava.framework.baseapplication.api.MenuController;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuControllerImpl implements MenuController {

    @Resource
    private MenuService menuService;

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<MenuListDetailVO>> list(@RequestBody MenuReqVO menuReqVO) {
        return JsonResult.<PageResult<MenuListDetailVO>>builder().success(menuService.list(menuReqVO)).build();
    }

    @Override
    @PostMapping("/insert")
    public JsonResult<Boolean> insert(@RequestBody MenuInsertVO menuInsertVO) {
        return JsonResult.<Boolean>builder().success(menuService.insert(menuInsertVO)).build();
    }

    @Override
    @PostMapping("/detail")
    public JsonResult<MenuListDetailVO> detail(@RequestBody MenuReqVO menuReqVO) {
        return JsonResult.<MenuListDetailVO>builder().success(menuService.detail(menuReqVO)).build();
    }

    @Override
    @PostMapping("/listTree")
    public JsonResult<List<MenuNodeDTO>> listTree(@RequestBody MenuReqDTO menuReqDTO) {
        return JsonResult.<List<MenuNodeDTO>>builder().success(menuService.listTree(menuReqDTO)).build();
    }

}
