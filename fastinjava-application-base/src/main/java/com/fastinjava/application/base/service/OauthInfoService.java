package com.fastinjava.application.base.service;

import com.fastinjava.framework.baseapplication.vo.*;
import com.fastinjava.framework.common.res.PageResult;

public interface OauthInfoService {
    PageResult<OauthListDetailsVO> list(OauthDetailReqVO oauthDetailReqVO);

    OauthListDetailsVO detail(OauthDetailReqVO oauthDetailReqVO);

    Boolean insert(OauthDetailsInsertVO oauthDetailsInsertVO);

    Boolean update(OauthDetailsUpdateVO oauthDetailsUpdateVO);

    Boolean grantClientApi(GrantClientApiVO grantClientApiVO);
}
