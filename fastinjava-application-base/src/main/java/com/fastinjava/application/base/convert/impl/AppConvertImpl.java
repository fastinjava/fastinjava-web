package com.fastinjava.application.base.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationReqDTO;
import com.fastinjava.application.base.convert.AppConvert;
import com.fastinjava.framework.baseapplication.vo.AppInsertVO;
import com.fastinjava.framework.baseapplication.vo.AppListDetailVO;
import com.fastinjava.framework.baseapplication.vo.AppListReqVO;
import org.springframework.stereotype.Component;

@Component
public class AppConvertImpl implements AppConvert {
    @Override
    public ApplicationInsertDTO appInsertVO2ApplicationInsertDTO(AppInsertVO appInsertVO) {
        if (ObjectUtil.isEmpty(appInsertVO)) return null;

        ApplicationInsertDTO applicationInsertDTO = new ApplicationInsertDTO();
        applicationInsertDTO.setAppName(appInsertVO.getAppName());
        applicationInsertDTO.setAppCode(appInsertVO.getAppCode());
        applicationInsertDTO.setAppDesc(appInsertVO.getAppDesc());
        applicationInsertDTO.setAppType(appInsertVO.getAppType());
        applicationInsertDTO.setDeleteFlag(appInsertVO.getDeleteFlag());
        applicationInsertDTO.setAppHttps(appInsertVO.getAppHttps());
        applicationInsertDTO.setAppHost(appInsertVO.getAppHost());
        applicationInsertDTO.setAppContext(appInsertVO.getAppContext());
        applicationInsertDTO.setAppPort(appInsertVO.getAppPort());
        return applicationInsertDTO;
    }

    @Override
    public ApplicationReqDTO appListReqVO2ApplicationReqDTO(AppListReqVO appListReqVO) {
        if (null == appListReqVO) return null;
        ApplicationReqDTO applicationReqDTO = new ApplicationReqDTO();
        applicationReqDTO.setPageable(appListReqVO.getPageable());
        applicationReqDTO.setPageNum(appListReqVO.getPageNum());
        applicationReqDTO.setPageSize(appListReqVO.getPageSize());
        return applicationReqDTO;
    }

    @Override
    public AppListDetailVO applicationDTO2AppListDetailVO(ApplicationDTO applicationDTO) {
        AppListDetailVO appListDetailVO = new AppListDetailVO();
        BeanUtil.copyProperties(applicationDTO, appListDetailVO);
        return appListDetailVO;
    }
}
