package com.fastinjava.application.web.controller;

import com.fastinjava.application.service.OauthInfoService;
import com.fastinjava.framework.baseapplication.api.OauthInfoController;
import com.fastinjava.framework.baseapplication.vo.OauthDetailReqVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsInsertVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsUpdateVO;
import com.fastinjava.framework.baseapplication.vo.OauthListDetailsVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
        PageResult<OauthListDetailsVO> pageResult = oauthInfoService.list(oauthDetailReqVO);
        return JsonResult.<PageResult<OauthListDetailsVO>>builder().success(pageResult).build();
    }

    @Override
    @PostMapping("/detail")
    public JsonResult<OauthListDetailsVO> detail(@RequestBody OauthDetailReqVO oauthDetailReqVO) {
        OauthListDetailsVO oauthListDetailsVO = oauthInfoService.detail(oauthDetailReqVO);
        return JsonResult.<OauthListDetailsVO>builder().success(oauthListDetailsVO).build();
    }


    @Override
    @PostMapping("/insert")
    public JsonResult<Boolean> insert(@RequestBody OauthDetailsInsertVO oauthDetailsInsertVO) {
        Boolean result = oauthInfoService.insert(oauthDetailsInsertVO);
        return JsonResult.<Boolean>builder().success(result).build();
    }

    @Override
    @PostMapping("/update")
    public JsonResult<Boolean> update(@RequestBody OauthDetailsUpdateVO oauthDetailsUpdateVO) {
        Boolean result = oauthInfoService.update(oauthDetailsUpdateVO);
        return JsonResult.<Boolean>builder().success(result).build();
    }

}
