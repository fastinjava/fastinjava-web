package com.fastinjava.application.base.convert;

import com.fastdevelopinjava.framework.system.api.dto.ApplicationDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationReqDTO;
import com.fastinjava.framework.baseapplication.vo.AppInsertVO;
import com.fastinjava.framework.baseapplication.vo.AppListDetailVO;
import com.fastinjava.framework.baseapplication.vo.AppListReqVO;

public interface AppConvert {
    AppListDetailVO applicationDTO2AppListDetailVO(ApplicationDTO applicationDTO);

    ApplicationReqDTO appListReqVO2ApplicationReqDTO(AppListReqVO appListReqVO);

    ApplicationInsertDTO appInsertVO2ApplicationInsertDTO(AppInsertVO appInsertVO);
}

