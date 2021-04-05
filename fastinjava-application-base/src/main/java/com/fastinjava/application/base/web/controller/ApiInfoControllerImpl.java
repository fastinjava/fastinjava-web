package com.fastinjava.application.base.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastinjava.application.base.service.ApiInfoService;
import com.fastinjava.framework.baseapplication.api.ApiInfoController;
import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import com.fastinjava.framework.common.vo.DictVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "接口管理", tags = {"接口管理api"})
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
        return result ? JsonResult.<Boolean>builder().success().build() : JsonResult.<Boolean>builder().failure("新增失败").build();
    }

    @Override
    @PostMapping("/dict")
    public JsonResult<List<DictVO>> dict() {
        ApiInfoReqVO apiInfoReqVO = new ApiInfoReqVO();
        apiInfoReqVO.setPageable(false);
        JsonResult<PageResult<ApiInfoListDetailVO>> jsonResult = this.list(apiInfoReqVO);
        if (jsonResult.getSuccess() && ObjectUtil.isNotEmpty(jsonResult.getData()) && CollectionUtil.isNotEmpty(jsonResult.getData().getList())) {
            List<DictVO> dictVOList = Lists.newArrayList();
            List<ApiInfoListDetailVO> apiInfoListDetailVOList = jsonResult.getData().getList();
            for (ApiInfoListDetailVO apiInfoListDetailVO : apiInfoListDetailVOList) {
                DictVO dictVO = new DictVO();
                dictVO.setDisabled(false);
                dictVO.setDesc(null);
                dictVO.setLabel(apiInfoListDetailVO.getApiName());
                dictVO.setValue(apiInfoListDetailVO.getApiCode());
                dictVOList.add(dictVO);
            }
            return JsonResult.<List<DictVO>>builder().success(dictVOList).build();
        } else {
            return JsonResult.<List<DictVO>>builder().success(Lists.newArrayList()).build();
        }

    }

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<ApiInfoListDetailVO>> list(@RequestBody ApiInfoReqVO apiInfoReqVO) {
        PageResult<ApiInfoListDetailVO> pageResult = apiInfoService.list(apiInfoReqVO);
        return JsonResult.<PageResult<ApiInfoListDetailVO>>builder().success(pageResult).build();
    }


}
