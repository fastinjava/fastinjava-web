package com.fastinjava.application.web.controller;

import com.fastinjava.application.service.ApiInfoService;
import com.fastinjava.framework.baseapplication.api.ApiInfoController;
import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "接口管理",tags = {"接口管理api"})
@Slf4j
@RestController
@RequestMapping("/apiinfo")
public class ApiInfoControllerImpl implements ApiInfoController {
    @Resource
    private ApiInfoService apiInfoService;

    @Override
    @PostMapping("/insertSelective")
    public JsonResult<Boolean> insertSelective(@RequestBody ApiInfoInsertVO apiInfoInsertVO) {
        Boolean result = apiInfoService.insertSelective(apiInfoInsertVO);
        return result ? JsonResult.<Boolean>builder().success().build(): JsonResult.<Boolean>builder().failure("新增失败").build();
    }

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<ApiInfoListDetailVO>> list(@RequestBody ApiInfoReqVO apiInfoReqVO) {
        PageResult<ApiInfoListDetailVO> pageResult = apiInfoService.list(apiInfoReqVO);
        return JsonResult.<PageResult<ApiInfoListDetailVO>>builder().success(pageResult).build();
    }
}
