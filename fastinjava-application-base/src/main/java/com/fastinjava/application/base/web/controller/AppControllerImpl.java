package com.fastinjava.application.base.web.controller;

import com.fastinjava.application.base.service.AppService;
import com.fastinjava.framework.baseapplication.api.AppController;
import com.fastinjava.framework.baseapplication.vo.AppInsertVO;
import com.fastinjava.framework.baseapplication.vo.AppListDetailVO;
import com.fastinjava.framework.baseapplication.vo.AppListReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "资源管理", tags = {"资源管理api"})
@RestController
@Slf4j
@RequestMapping("/app")
public class AppControllerImpl implements AppController {

    @Resource
    private AppService appService;

    @PostMapping("/list")
    @Override
    public JsonResult<PageResult<AppListDetailVO>> list(@RequestBody AppListReqVO appListReqVO) {
        PageResult<AppListDetailVO> pageResult = appService.list(appListReqVO);
        return JsonResult.<PageResult<AppListDetailVO>>builder().success(pageResult).build();
    }

    @PostMapping("/insert")
    @Override
    public JsonResult<Boolean> insert(@RequestBody AppInsertVO appInsertVO) {
        return appService.insertSelective(appInsertVO) ? JsonResult.<Boolean>builder().success().build() : JsonResult.<Boolean>builder().failure("新增资源失败").build();
    }
}
