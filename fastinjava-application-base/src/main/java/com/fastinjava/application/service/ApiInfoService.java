package com.fastinjava.application.service;

import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;
import com.fastinjava.framework.common.res.PageResult;

public interface ApiInfoService {
    PageResult<ApiInfoListDetailVO> list(ApiInfoReqVO apiInfoReqVO);

    Boolean insertSelective(ApiInfoInsertVO apiInfoInsertVO);
}
