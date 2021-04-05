package com.fastinjava.application.base.web.controller;


import com.fastinjava.application.base.service.SysDictService;
import com.fastinjava.framework.baseapplication.api.SysDictController;
import com.fastinjava.framework.baseapplication.vo.SysDictInsertVO;
import com.fastinjava.framework.baseapplication.vo.SysDictListDetailVO;
import com.fastinjava.framework.baseapplication.vo.SysDictReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/sys_dict")
@RestController
public class SysDictControllerImpl implements SysDictController {

    @Resource
    private SysDictService sysDictService;

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<SysDictListDetailVO>> list(@RequestBody SysDictReqVO sysDictReqVO) {
        return JsonResult.<PageResult<SysDictListDetailVO>>builder().success(sysDictService.list(sysDictReqVO)).build();
    }

    @Override
    @PostMapping("/insert")
    public JsonResult<Boolean> insert(@RequestBody SysDictInsertVO sysDictInsertVO) {
        Boolean result = sysDictService.insert(sysDictInsertVO);
        return result ? JsonResult.<Boolean>builder().success().build() : JsonResult.<Boolean>builder().failure().build();
    }
}
