package com.fastinjava.application.service;

import com.fastinjava.framework.baseapplication.vo.OauthDetailReqVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsInsertVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsUpdateVO;
import com.fastinjava.framework.baseapplication.vo.OauthListDetailsVO;
import com.fastinjava.framework.common.res.PageResult;

public interface OauthInfoService {
    PageResult<OauthListDetailsVO> list(OauthDetailReqVO oauthDetailReqVO);

    OauthListDetailsVO detail(OauthDetailReqVO oauthDetailReqVO);

    Boolean insert(OauthDetailsInsertVO oauthDetailsInsertVO);

    Boolean update(OauthDetailsUpdateVO oauthDetailsUpdateVO);
}
