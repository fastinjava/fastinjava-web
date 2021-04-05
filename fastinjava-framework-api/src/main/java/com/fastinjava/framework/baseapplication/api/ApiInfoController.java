package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;
import com.fastinjava.framework.common.vo.DictVO;

import java.util.List;

public interface ApiInfoController {
    JsonResult<PageResult<ApiInfoListDetailVO>> list(ApiInfoReqVO apiInfoReqVO);
    JsonResult<Boolean> insertSelective(ApiInfoInsertVO apiInfoInsertVO);
    JsonResult<List<DictVO>> dict();
}
