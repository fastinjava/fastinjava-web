package com.fastinjava.application.base.convert.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.system.api.dto.*;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.AppFeginClient;
import com.fastinjava.application.base.convert.ApiInfoConvert;
import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ApiInfoConvertImpl implements ApiInfoConvert {

    @Resource
    private AppFeginClient appFeginClient;

    @Override
    public ApiInfoReqDTO apiInfoReqVO2ApiInfoReqDTO(ApiInfoReqVO apiInfoReqVO) {
        if (null == apiInfoReqVO) return null;
        ApiInfoReqDTO apiInfoReqDTO = new ApiInfoReqDTO();
        //apiInfoReqDTO.setApiCode(apiInfoReqVO.getApiCode());
        BeanUtil.copyProperties(apiInfoReqVO,apiInfoReqDTO);
        return apiInfoReqDTO;
    }

    @Override
    public ApiInfoInsertDTO apiInfoInsertVO2ApiInfoInsertDTO(ApiInfoInsertVO apiInfoInsertVO) {
        if (null == apiInfoInsertVO) return null;

        ApiInfoInsertDTO apiInfoInsertDTO = new ApiInfoInsertDTO();

        BeanUtil.copyProperties(apiInfoInsertVO,apiInfoInsertDTO);

        return apiInfoInsertDTO;
    }

    @Override
    public ApiInfoListDetailVO apiInfoDTO2ApiInfoListDetailVO(ApiInfoDTO apiInfoDTO) {
        if (null == apiInfoDTO) return null;
        ApiInfoListDetailVO apiInfoListDetailVO = new ApiInfoListDetailVO();
        BeanUtil.copyProperties(apiInfoDTO,apiInfoListDetailVO);

        if (ObjectUtil.isNotEmpty(apiInfoDTO.getCreatTime())){
            apiInfoListDetailVO.setCreatTimeStr(DateUtil.formatDateTime(apiInfoDTO.getCreatTime()));
        }


        if (ObjectUtil.isNotEmpty(apiInfoDTO.getUpdateTime())){
            apiInfoListDetailVO.setUpdateTimeStr(DateUtil.formatDateTime(apiInfoDTO.getUpdateTime()));
        }

        Integer appId = apiInfoDTO.getAppId();
        if (ObjectUtil.isNotEmpty(appId))
        {
            ApplicationReqDTO applicationReqDTO = new ApplicationReqDTO();
            applicationReqDTO.setAppId(appId);
            ResultDTO<ApplicationDTO> resultDTO = appFeginClient.getOne(applicationReqDTO);
            if (resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData()))
            {
                ApplicationDTO applicationDTO = resultDTO.getData();
                String appName = applicationDTO.getAppName();
                apiInfoListDetailVO.setAppName(appName);
            }
        }

        return apiInfoListDetailVO;
    }
}
