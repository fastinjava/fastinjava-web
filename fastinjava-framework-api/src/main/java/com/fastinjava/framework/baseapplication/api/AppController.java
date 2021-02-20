package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.AppInsertVO;
import com.fastinjava.framework.baseapplication.vo.AppListDetailVO;
import com.fastinjava.framework.baseapplication.vo.AppListReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

public interface AppController {

    JsonResult<PageResult<AppListDetailVO>> list(AppListReqVO appListReqVO);
    JsonResult<Boolean> insert(AppInsertVO appInsertVO);

}
