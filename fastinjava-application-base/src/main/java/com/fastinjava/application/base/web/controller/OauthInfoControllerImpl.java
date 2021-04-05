package com.fastinjava.application.base.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fastinjava.application.base.service.OauthInfoService;
import com.fastinjava.framework.baseapplication.api.OauthInfoController;
import com.fastinjava.framework.baseapplication.vo.*;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "客户端接口管理", tags = {"客户端接口管理api"})
@Slf4j
@RestController
@RequestMapping("/oauth/info")
public class OauthInfoControllerImpl  implements OauthInfoController {

    @Resource
    private OauthInfoService oauthInfoService;

    @Override
    @PostMapping("/list")
    public JsonResult<PageResult<OauthListDetailsVO>> list(@RequestBody OauthDetailReqVO oauthDetailReqVO) {
        return JsonResult.<PageResult<OauthListDetailsVO>>builder().success(oauthInfoService.list(oauthDetailReqVO)).build();
    }

    @Override
    @PostMapping("/detail")
    public JsonResult<OauthListDetailsVO> detail(@RequestBody OauthDetailReqVO oauthDetailReqVO) {
        return JsonResult.<OauthListDetailsVO>builder().success(oauthInfoService.detail(oauthDetailReqVO)).build();
    }


    @Override
    @PostMapping("/insert")
    public JsonResult<Boolean> insert(@RequestBody OauthDetailsInsertVO oauthDetailsInsertVO) {
        Boolean result = oauthInfoService.insert(oauthDetailsInsertVO);
        return result ? JsonResult.<Boolean>builder().success(true).build() : JsonResult.<Boolean>builder().failure().build();
    }

    @Override
    @PostMapping("/update")
    public JsonResult<Boolean> update(@RequestBody OauthDetailsUpdateVO oauthDetailsUpdateVO) {
        Boolean result = oauthInfoService.update(oauthDetailsUpdateVO);
        return result ? JsonResult.<Boolean>builder().success(true).build() : JsonResult.<Boolean>builder().failure().build();
    }


    @Override
    @PostMapping("/grantClientApi")
    public JsonResult<Boolean> grantClientApi(@RequestBody GrantClientApiVO grantClientApiVO) {

        String clientId = grantClientApiVO.getClientId();
        List<String> apiCodeList = grantClientApiVO.getApiCodeList();
        if (StrUtil.isBlank(clientId)){
            throw new RuntimeException("clientId must not null");
        }
        if (CollectionUtil.isEmpty(apiCodeList)){
            throw new RuntimeException("apiCodeList must not null");
        }else {
            apiCodeList = apiCodeList.stream().filter(StrUtil::isNotBlank).collect(Collectors.toList());
            grantClientApiVO.setApiCodeList(apiCodeList);
        }

        Boolean result = oauthInfoService.grantClientApi(grantClientApiVO);
        return result ? JsonResult.<Boolean>builder().success(true).build() : JsonResult.<Boolean>builder().failure().build();
    }
}
