package com.fastinjava.application.service;

import com.fastinjava.framework.baseapplication.vo.AppInsertVO;
import com.fastinjava.framework.baseapplication.vo.AppListDetailVO;
import com.fastinjava.framework.baseapplication.vo.AppListReqVO;
import com.fastinjava.framework.common.res.PageResult;

public interface AppService {
    PageResult<AppListDetailVO> list(AppListReqVO appListReqVO);

    boolean insertSelective(AppInsertVO appInsertVO);
}
