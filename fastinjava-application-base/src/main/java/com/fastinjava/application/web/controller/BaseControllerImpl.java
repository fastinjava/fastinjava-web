package com.fastinjava.application.web.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.AppFeginClient;
import com.fastinjava.framework.common.res.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Api("BaseControllerImpl")
@Slf4j
@RequestMapping("/v/vv")
@RestController
public class BaseControllerImpl {


    @Resource
    private AppFeginClient appFeginClient;

    @PostMapping("/listApi")
    @ApiOperation("listApi")
    public JsonResult<List<ApiInfoDTO>> listApi(@RequestBody JSONObject jsonObject) {

        try {
            Integer appId = jsonObject.getInteger("appId");
            String apiCode = jsonObject.getString("apiCode");
            ApplicationReqDTO applicationReqDTO = new ApplicationReqDTO();
            applicationReqDTO.setAppId(appId);
            ResultDTO<ApplicationDTO> resultDTO = appFeginClient.getOne(applicationReqDTO);

            StringBuilder sb = new StringBuilder();

            if (resultDTO.getSuccess()) {
                ApplicationDTO applicationDTO = resultDTO.getData();
                Integer https = applicationDTO.getAppHttps();
                //是否https
                boolean httpEnable = ObjectUtil.equal("1", https);
                if (httpEnable) {
                    sb.append("https://");
                } else {
                    sb.append("http://");
                }
                sb.append(applicationDTO.getAppHost()).append(":").append(applicationDTO.getAppPort()).append("/").append(applicationDTO.getAppContext()).append("/v/vv/apilist");
                String body = HttpUtil.createGet(sb.toString()).timeout(5000 * 2).execute().body();
                if (StrUtil.isNotBlank(body)) {
                    ResultDTO<List<ApiInfoDTO>> listResultDTO = JSON.parseObject(body, new TypeReference<ResultDTO<List<ApiInfoDTO>>>() {
                    });
                    if (listResultDTO.getSuccess()) {
                        List<ApiInfoDTO> apiInfoDTOList = listResultDTO.getData().stream()
                                .filter(apiInfoDTO -> !StrUtil.isBlank(apiInfoDTO.getApiUrl()))
                                .filter(apiInfoDTO -> StrUtil.isNotBlank(apiCode) ? (apiCode.equalsIgnoreCase(apiInfoDTO.getApiCode()) ? true : false) : true)
                                .collect(Collectors.toList());
                        return JsonResult.<List<ApiInfoDTO>>builder().success(apiInfoDTOList).build();
                    }
                }
            }
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage());
        }

        return JsonResult.<List<ApiInfoDTO>>builder().failure("获取接口基础信息失败").build();
    }
}
